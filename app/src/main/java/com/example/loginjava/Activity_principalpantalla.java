package com.example.loginjava;


import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class Activity_principalpantalla extends AppCompatActivity {

    TextView textViewUsuario,emailTxt;
    private Switch swCerrarSesion;

    private ImageButton imgBtnOffSesion;
    private ListView lvMenuPrincipal;
    private GoogleApiClient googleApiClient;

    private CardView cardViewUniverso,cardViewMaps,cardViewCamera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principalpantalla);
        cardViewUniverso = findViewById(R.id.cardViewUniverso);
        cardViewMaps = findViewById(R.id.cardViewMaps);
        cardViewCamera = findViewById(R.id.cardViewCamera);
        swCerrarSesion = findViewById(R.id.swCerrarSesion);

        textViewUsuario = findViewById(R.id.tvUsuario);
        emailTxt = findViewById(R.id.emailtxt);

        cardViewUniverso.setOnClickListener(View -> getUniverso());
        cardViewMaps.setOnClickListener(View -> getMaps());
        cardViewCamera.setOnClickListener(View -> getCamera());
        getUserProfile();
    }


    public void getUniverso(){
        Intent intPlanetas = new Intent(getApplicationContext(), Activity_planetas.class);
        startActivity(intPlanetas);
    }
    public void getMaps(){
        Intent intGogleMaps = new Intent(getApplicationContext(), Activity_googlemaps.class);
        startActivity(intGogleMaps);
    }
    public void getCamera(){
        Intent intCamera = new Intent(getApplicationContext(), Activity_Camera.class);
        startActivity(intCamera);
    }

    public void getUserProfile() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            textViewUsuario.setText(user.getDisplayName());
            emailTxt.setText(user.getEmail());
        }

    }
    public void cerrarSesion(){
        textViewUsuario.setText("");
        emailTxt.setText("");
        Toast.makeText(getApplicationContext(), "Sesión cerrada", Toast.LENGTH_SHORT).show();
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), AuthActivity.class));
    }

    public void onCerrarSesion(View view) {
        if(view.getId() == R.id.swCerrarSesion){
            if(!swCerrarSesion.isChecked()){
                cerrarSesion();
                return;
            }
        }
    }
}