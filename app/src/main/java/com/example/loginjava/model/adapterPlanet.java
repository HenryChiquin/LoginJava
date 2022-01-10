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
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

public class adapterPlanet extends BaseAdapter {


    private   ArrayList<Planetas> modelArrayList;
    private Context context;
    private int layout;
    //generate constructor

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
        TextView idtxt, nombretxt, tipotxt;
        ImageView imagenTv;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        ViewHolder viewHolder = new ViewHolder();
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(layout,null);
        //id type casting
        viewHolder.idtxt = view.findViewById(R.id.idtxt);
        viewHolder.nombretxt = view.findViewById(R.id.nombretxt);
        viewHolder.tipotxt = view.findViewById(R.id.tipotxt);
        viewHolder.imagenTv = view.findViewById(R.id.iViewImagenPlanet);

        //setPosition
        Planetas modelPlanetas = modelArrayList.get(position);
        viewHolder.idtxt.setText("id: "+modelPlanetas.getId()+"\n");
        viewHolder.nombretxt.setText("nombre: "+modelPlanetas.getNombre()+"\n");
        viewHolder.tipotxt.setText("tipo: "+modelPlanetas.getTipo()+"\n");

        Picasso.with(context.getApplicationContext())
                .load(modelPlanetas.getImagen())
                .error(R.mipmap.ic_launcher)
                .fit()
                .centerInside()
                .into(viewHolder.imagenTv);


        Universo oUniverso = new Universo();
        oUniverso.getId();
        Log.e("Erorr CALL_API UNIVERSO en onResponse: ", String.valueOf(oUniverso.getId()));
        return view;
    }


}
