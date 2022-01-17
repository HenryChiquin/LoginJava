package com.example.loginjava;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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
    private Button btnCerrarsesion;
    private ListView lvMenuPrincipal;
    private GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principalpantalla);
        textViewUsuario = findViewById(R.id.tvUsuario);
        emailTxt = findViewById(R.id.emailtxt);
        btnCerrarsesion = findViewById(R.id.btnCerrarSesion);
        lvMenuPrincipal = findViewById(R.id.lvMenuPrincipal);

        btnCerrarsesion.setOnClickListener(View ->cerrarSesion());
        //List view CAJA
        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,listaOpciones());
        lvMenuPrincipal.setAdapter(arrayAdapter);
        menuOpciones();
        getUserProfile();
    }

    //Menu de opciones
    public void menuOpciones(){
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
                    case 2:
                        Intent intCamera = new Intent(getApplicationContext(), Activity_Camera.class);
                        startActivity(intCamera);
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "Opcion incorrecto", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }
    public void getUserProfile() {
        // [START get_user_profile]
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            textViewUsuario.setText(user.getDisplayName());
            emailTxt.setText(user.getEmail());
            Uri photoUrl = user.getPhotoUrl();
            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();
            String uid = user.getUid();
        }

    }
    public void cerrarSesion(){
        textViewUsuario.setText("");
        emailTxt.setText("");
        Toast.makeText(getApplicationContext(), "Sesión cerrada", Toast.LENGTH_SHORT).show();
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), AuthActivity.class));
    }
    public static List listaOpciones(){
        List<String> list = new ArrayList<>();
        list.add("Planetas");
        list.add("Google Maps");
        list.add("Camera");
        return list;
    }

}