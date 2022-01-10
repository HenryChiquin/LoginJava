package com.example.loginjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.loginjava.Api.apiPlanetas;

import com.example.loginjava.Api.apiUniverso;
import com.example.loginjava.model.Planetas;
import com.example.loginjava.Util.conectionRetrofit;
import com.example.loginjava.model.Universo;
import com.example.loginjava.model.adapterPlanet;
import com.example.loginjava.model.listadoPlanetas;
import com.example.loginjava.model.listadoUniverse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrincipalPantalla extends AppCompatActivity {

    TextView textViewUsuario;


    ArrayList<Universo> modelArrayUniverse;

    private ListView lv1Planetas;
    private static final String TAG = "PLANET";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_pantalla);
        textViewUsuario = findViewById(R.id.tvUsuario);

        //List view CAJA
        lv1Planetas = findViewById(R.id.lvPlanetas);

        modelArrayUniverse = new ArrayList<>();

        Intent intent = getIntent();
        String usuario = intent.getStringExtra("usuario");
        String id = intent.getStringExtra("id");

        textViewUsuario.setText("Bienvenido "+usuario +"!");
        listViewPlanetas();

    }

    //Funcion listar Planetas
    public void listViewPlanetas(){
        String urlRutaPlanet = "https://g2c0586b3c2559e-pdc.adb.us-ashburn-1.oraclecloudapps.com/ords/admin/detail/";
        String urlRutaUniverse = "https://g2c0586b3c2559e-pdc.adb.us-ashburn-1.oraclecloudapps.com/ords/admin/detail/";


        apiPlanetas oApiPlanetas = conectionRetrofit.getConection(urlRutaPlanet).create(apiPlanetas.class);
        apiUniverso oApiUniverso = conectionRetrofit.getConection(urlRutaUniverse).create(apiUniverso.class);

        Call<listadoPlanetas> CalloListPlanetas = oApiPlanetas.getPlanetas();
        Call<listadoUniverse> CalloListUniverse = oApiUniverso.getUniverso();


        CalloListPlanetas.enqueue(new Callback<listadoPlanetas>() {
            @Override
            public void onResponse(Call<listadoPlanetas> call, Response<listadoPlanetas> response) {
                if (!response.isSuccessful()) {
                    Log.e("Erorr CALL_API PLANETA en onResponse: ", String.valueOf(response.code()));
                    return;
                }
                //BODY
                listadoPlanetas listPlanetas = response.body();
                //List Array
                ArrayList<Planetas> olistaPlanetas = listPlanetas.getItems();
                //create adapter
                adapterPlanet oAdapterPlanet = new adapterPlanet(olistaPlanetas,PrincipalPantalla.this,R.layout.singleview);
                lv1Planetas.setAdapter(oAdapterPlanet);
            }

            @Override
            public void onFailure(Call<listadoPlanetas> call, Throwable t) {
                Log.e("CALL_API PLANETA ¡ERROR! en onFailure: ", t.getMessage());
            }
        });

        CalloListUniverse.enqueue(new Callback<listadoUniverse>() {
            @Override
            public void onResponse(Call<listadoUniverse> call, Response<listadoUniverse> response) {
                if (!response.isSuccessful()) {
                    Log.e("Erorr CALL_API UNIVERSO en onResponse: ", String.valueOf(response.code()));
                    return;
                }
                //BODY
                listadoUniverse listUniverse = response.body();
                //List Array
                ArrayList<Universo> olistaUniverse = listUniverse.getItems();

                for(int i=0;i < olistaUniverse.size(); i++);
                Log.e(TAG,"Universo: "+ olistaUniverse);
            }

            @Override
            public void onFailure(Call<listadoUniverse> call, Throwable t) {
                Log.e("CALL_API UNIVERSO ¡ERROR! en onFailure: ", t.getMessage());
            }
        });
    }



}