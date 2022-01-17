package com.example.loginjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileInputStream;

public class Activity_galeria extends AppCompatActivity {
    String []archivos;
    RecyclerView rvGaleria;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);
        archivos=fileList();
        rvGaleria = findViewById(R.id.rvGaleria);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvGaleria.setLayoutManager(linearLayoutManager);
        rvGaleria.setAdapter(new AdaptadorFotos());
    }

    private class AdaptadorFotos extends RecyclerView.Adapter<AdaptadorFotos.adaptadorfotosholder> {
        @NonNull
        @Override
        public adaptadorfotosholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new adaptadorfotosholder(getLayoutInflater().inflate(R.layout.layout_foto,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull adaptadorfotosholder holder, int position) {
            holder.imprimir(position);
        }

        @Override
        public int getItemCount() {
            return archivos.length;
        }

        class adaptadorfotosholder extends RecyclerView.ViewHolder{
            ImageView imgVieFotos;
            TextView txtnombreFoto;
            public adaptadorfotosholder(@NonNull View itemView) {
                super(itemView);
                imgVieFotos = itemView.findViewById(R.id.imgViewFoto);
                txtnombreFoto = itemView.findViewById(R.id.txtNombreFoto);
            }

            public void imprimir(int position) {
                txtnombreFoto.setText("Nombre del archivo: "+archivos[position]);
                try {
                    FileInputStream fileInputStream = openFileInput(archivos[position]);
                    Bitmap bitmap = BitmapFactory.decodeStream(fileInputStream);
                    imgVieFotos.setImageBitmap(bitmap);
                    fileInputStream.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}