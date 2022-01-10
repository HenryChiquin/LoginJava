package com.example.loginjava;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginjava.Api.apiPlanetas;
import com.example.loginjava.Api.apiUsuario;
import com.example.loginjava.Api.serviceApi;

import com.example.loginjava.Util.conectionRetrofit;
import com.example.loginjava.model.Planetas;
import com.example.loginjava.model.usuarioGet;

import java.util.List;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText cajaNombre;
    EditText cajaContra;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cajaNombre = findViewById(R.id.cajaName);
        cajaContra = findViewById(R.id.cajaPassword);

        button = findViewById(R.id.button);

        button.setOnClickListener(view -> getUsuarioRetrofit());

    }

    private void getUsuarioRetrofit() {


        String cNombre = cajaNombre.getText().toString();
        String cContra = cajaContra.getText().toString();


        /*
        if(!validarEmail(cNombre)){
            cajaNombre.setError("Email no valido");
            //Toast.makeText(this, "Email no válido", Toast.LENGTH_SHORT).show();
        }**/

        if (cNombre.isEmpty()) {
            cajaNombre.setError("El campo está vacío");
            return;
        }

        if (cContra.isEmpty()) {
            cajaContra.setError("El campo está vacío");
            return;
        }
        userLoginAutentication();


    }

    //Funcion Autenticacion Usuario
    public void userLoginAutentication(){

        String cNombre = cajaNombre.getText().toString();
        String cContra = cajaContra.getText().toString();
/*
        String urlRuta = "https://g2c0586b3c2559e-pdc.adb.us-ashburn-1.oraclecloudapps.com/ords/admin/auth/";

        apiUsuario oApiUsuario = conectionRetrofit.getConection(urlRuta).create(apiUsuario.class);
**/
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://g2c0586b3c2559e-pdc.adb.us-ashburn-1.oraclecloudapps.com/ords/admin/auth/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        apiUsuario oApiUser = retrofit.create(apiUsuario.class);

        Call<usuarioGet> call = oApiUser.getUsuario(cNombre, cContra);
        call.enqueue(new Callback<usuarioGet>() {
                         @Override
                         public void onResponse(Call<usuarioGet> call, Response<usuarioGet> response) {
                             if (!response.isSuccessful()) {
                                 Log.e("CALL_API", String.valueOf(response.code()));
                                 return;
                             }

                             usuarioGet obtenerUsuario = response.body();


                             assert obtenerUsuario != null;

                             Intent intent = new Intent(getApplicationContext(), PrincipalPantalla.class);
                             intent.putExtra("usuario", obtenerUsuario.getUsuario());
                             intent.putExtra("id", obtenerUsuario.getId());
                             intent.putExtra("estado", obtenerUsuario.getEstado());
                             intent.putExtra("tipo", obtenerUsuario.getTipo());
                             startActivity(intent);

                         }

                         @Override
                         public void onFailure(Call<usuarioGet> call, Throwable t) {
                             Log.e("CALL_API", t.getMessage());
                         }
        });
    }





    public void btnLogin(View view) {


    }


    //Metodo para validar Email
    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
}