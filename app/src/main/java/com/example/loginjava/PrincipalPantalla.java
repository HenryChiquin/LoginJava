package com.example.loginjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PrincipalPantalla extends AppCompatActivity {

    TextView textViewUsuario;


    private ListView lvMenuPrincipal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_pantalla);
        textViewUsuario = findViewById(R.id.tvUsuario);

        //List view CAJA
        lvMenuPrincipal = findViewById(R.id.lvMenuPrincipal);

        List<String> list = new ArrayList<>();
        list.add("Planetas");
        list.add("Google Maps");

        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,list);
        lvMenuPrincipal.setAdapter(arrayAdapter);

        lvMenuPrincipal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                switch(position){
                    case 0:
                        Intent intPlanetas = new Intent(getApplicationContext(), Activity_planetas.class);
                        startActivity(intPlanetas);
                        break;
                    case 1:
                        Intent intGogleMaps = new Intent(getApplicationContext(), Activity_googlemaps.class);
                        startActivity(intGogleMaps);
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "Opcion incorrecto", Toast.LENGTH_SHORT).show();
                        break;

                }
                if(position == 0){
                    //Clicked apple
                }else if(position == 1){

                }
            }
        });



        Intent intent = getIntent();
        String usuario = intent.getStringExtra("usuario");
        String id = intent.getStringExtra("id");

        textViewUsuario.setText("Bienvenido "+usuario +"!");


    }



}