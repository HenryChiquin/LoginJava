package com.example.loginjava.model;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.loginjava.R;
import com.example.loginjava.database.AppDatabase;
import com.example.loginjava.database.entity.tUniverso;
import com.example.loginjava.repository.universoRepository;
import com.example.loginjava.repository.universoRepositoryImpl;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.List;

public class adapterPlanet extends BaseAdapter {


    private   ArrayList<Planetas> modelArrayList;
    private Context context;
    private int layout;
    //generar constructor
    public adapterPlanet(ArrayList<Planetas> modelArrayList, Context context, int layout) {
        this.modelArrayList = modelArrayList;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public int getCount()
    {
        return modelArrayList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return modelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //Create view holder innter class
    private class ViewHolder{
        TextView idtxt, nombretxt, categoriatxt;
        ImageView imagenTv;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        //Instanciar base de datos
        AppDatabase db = AppDatabase.getInstance(this.context);
        //Instanciar repositorio
        universoRepository repo = new universoRepositoryImpl(db.uniiversoDao());

        //Creacion de view
        ViewHolder viewHolder = new ViewHolder();
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(layout,null);
        //id type casting
        viewHolder.idtxt = view.findViewById(R.id.idtxt);
        viewHolder.nombretxt = view.findViewById(R.id.nombretxt);
        viewHolder.categoriatxt = view.findViewById(R.id.categoriatxt);
        viewHolder.imagenTv = view.findViewById(R.id.iViewImagenPlanet);

        //agregar datos en posicion
        Planetas modelPlanetas = modelArrayList.get(position);
        viewHolder.idtxt.setText("No.: "+modelPlanetas.getId()+"\n");
        viewHolder.nombretxt.setText("Nombre: "+modelPlanetas.getNombre()+"\n");

        Picasso.with(context.getApplicationContext())
                .load(modelPlanetas.getImagen())//URL DE LA IMAGEN
                .error(R.mipmap.ic_launcher)
                .fit()
                .centerInside()
                .into(viewHolder.imagenTv);

        List<tUniverso> ltUniverso = repo.getAllUniverso();
        for(tUniverso x: ltUniverso){

            if(modelPlanetas.getTipo() == x.getId()){
                viewHolder.categoriatxt.setText("Categoría: "+x.getNombre()+"\n");
            }
        }

        return view;
    }


}
