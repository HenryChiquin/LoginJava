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

import com.example.loginjava.Interface.userAutenticacion;
import com.example.loginjava.model.usuarioGet;

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
    private TextView textViewResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cajaNombre = findViewById(R.id.cajaName);
        cajaContra = findViewById(R.id.cajaPassword);
        textViewResultado = findViewById(R.id.txtResultado);
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


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://g2c0586b3c2559e-pdc.adb.us-ashburn-1.oraclecloudapps.com/ords/admin/auth/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        userAutenticacion oUserAutentication = retrofit.create(userAutenticacion.class);
        Call<usuarioGet> call = oUserAutentication.getUsuario(cNombre, cContra);
        call.enqueue(new Callback<usuarioGet>() {
            @Override
            public void onResponse(Call<usuarioGet> call, Response<usuarioGet> response) {
                if (!response.isSuccessful()) {
                    Log.e("CALL_API", String.valueOf(response.code()));
                    return;
                }

                usuarioGet obtenerUsuario = response.body();


                assert obtenerUsuario != null;
                Log.i("CALL_API", String.valueOf(obtenerUsuario.getUsuario()));
                Log.i("CALL_API", String.valueOf(obtenerUsuario.getId()));
                Log.i("CALL_API", String.valueOf(obtenerUsuario.getEstado()));
                Log.i("CALL_API", String.valueOf(obtenerUsuario.getTipo()));


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