package com.example.loginjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class PrincipalPantalla extends AppCompatActivity {

    TextView textViewUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_pantalla);
        textViewUsuario = findViewById(R.id.tvUsuario);

        String dato = getIntent().getStringExtra("dato");
        textViewUsuario.setText("Bienvenido "+dato +"!");


        Intent intent = getIntent();
        String usuario = intent.getStringExtra("usuario");
        String id = intent.getStringExtra("id");


    }



}