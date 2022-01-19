package com.example.loginjava;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.loginjava.Api.serviceApi;
import com.example.loginjava.Util.conectionRetrofit;
import com.example.loginjava.database.AppDatabase;
import com.example.loginjava.database.entity.tUniverso;
import com.example.loginjava.model.Planetas;
import com.example.loginjava.model.Universo;
import com.example.loginjava.model.adapterPlanet;
import com.example.loginjava.model.listadoPlanetas;
import com.example.loginjava.model.listadoUniverse;
import com.example.loginjava.repository.universoRepository;
import com.example.loginjava.repository.universoRepositoryImpl;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_planetas extends AppCompatActivity {

    private ListView lv1Planetas;
    private ImageView imgClosePlanetas;
    private ProgressBar prBcargarPlanetas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planetas);
        //List view CAJA
        lv1Planetas = findViewById(R.id.lvMenuPrincipal);
        imgClosePlanetas = findViewById(R.id.imgCloseUniverso);
        prBcargarPlanetas = findViewById(R.id.prBcargarPlanetas);

        imgClosePlanetas.setOnClickListener(view -> {
            onBackPressed();
        });
        listViewPlanetas();

    }

    //Funcion listar Planetas
    public void listViewPlanetas(){
        //URL DE LOS APIs
        String urlRutaPlanet = "https://g2c0586b3c2559e-pdc.adb.us-ashburn-1.oraclecloudapps.com/ords/admin/detail/";
        String urlRutaUniverse = "https://g2c0586b3c2559e-pdc.adb.us-ashburn-1.oraclecloudapps.com/ords/admin/detail/";

        //Instanciar base de datos
        AppDatabase db = AppDatabase.getInstance(this.getApplicationContext());
        //Instanciar repositorio
        universoRepository repo = new universoRepositoryImpl(db.uniiversoDao());

        //API objetos
        serviceApi oApiPlanetas = conectionRetrofit.getConection(urlRutaPlanet).create(serviceApi.class);
        serviceApi oApiUniverso = conectionRetrofit.getConection(urlRutaUniverse).create(serviceApi.class);

        Call<listadoPlanetas> CalloListPlanetas = oApiPlanetas.getPlanetas();
        Call<listadoUniverse> CalloListUniverse = oApiUniverso.getUniverso();

        //Llamada de la lista de los planetas
        CalloListPlanetas.enqueue(new Callback<listadoPlanetas>() {
                @Override
                public void onResponse(Call<listadoPlanetas> call, Response<listadoPlanetas> response) {
                    if (!response.isSuccessful()) {
                        Log.e("Error planet CALL_API en onResponse: ", String.valueOf(response.code()));
                        return;
                    }
                    //BODY
                    listadoPlanetas listPlanetas = response.body();
                    //List Array
                    ArrayList<Planetas> olistaPlanetas = listPlanetas.getItems();
                    //create adapter
                    adapterPlanet oAdapterPlanet = new adapterPlanet(olistaPlanetas,Activity_planetas.this,R.layout.planetas_layout_listaplanetas);
                    lv1Planetas.setAdapter(oAdapterPlanet);
                    prBcargarPlanetas.setVisibility(View.GONE);
                }
                @Override
                public void onFailure(Call<listadoPlanetas> call, Throwable t) {
                    Log.e("CALL_API PLANETA ¡ERROR! en onFailure: ", t.getMessage());
                }
        });

        //Llamada de la lista de universo y otros
        CalloListUniverse.enqueue(new Callback<listadoUniverse>() {
                @Override
                public void onResponse(Call<listadoUniverse> call, Response<listadoUniverse> response) {
                    if (!response.isSuccessful()) {
                        Log.e("Error CALL_API UNIVERSO en onResponse: ", String.valueOf(response.code()));
                        return;
                    }
                    //BODY
                    listadoUniverse listUniverse = response.body();
                    //List Array
                    ArrayList<Universo> olistaUniverse = listUniverse.getItems();

                    for(int i=0;i < olistaUniverse.size(); i++){
                        tUniverso otUniverso = new tUniverso();
                        Universo p = olistaUniverse.get(i);
                        otUniverso.setId(p.getId());
                        otUniverso.setNombre(p.getNombre());
                        repo.inserttUniverso(otUniverso);
                    }
                }
                @Override
                public void onFailure(Call<listadoUniverse> call, Throwable t) {
                    Log.e("CALL_API UNIVERSO ¡ERROR! en onFailure: ", t.getMessage());
                }
        });
    }


}