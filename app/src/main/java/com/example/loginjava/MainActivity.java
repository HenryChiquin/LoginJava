package com.example.loginjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    private TextView textViewResultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cajaNombre = findViewById(R.id.cajaName);
        cajaContra = findViewById(R.id.cajaPassword);
        textViewResultado = findViewById(R.id.txtResultado);
        getUsuario();
    }

    public void btnLogin(View view){
        String cNombre = cajaNombre.getText().toString();
        String cContra = cajaContra.getText().toString();


        /*
        if(!validarEmail(cNombre)){
            cajaNombre.setError("Email no valido");
            //Toast.makeText(this, "Email no válido", Toast.LENGTH_SHORT).show();
        }**/

        if(cNombre.isEmpty()){
            cajaNombre.setError("El campo está vacío");
        }
        else if(cContra.isEmpty()){
            cajaContra.setError("El campo está vacío");
        }
        else{
            if(cNombre.equals("henry") && cContra.equals("123") ){
                Toast.makeText(this, "Bienvenido!", Toast.LENGTH_SHORT).show();
                Intent oLoginIntent = new Intent(this,PrincipalPantalla.class);
                oLoginIntent.putExtra("dato",cNombre);

                startActivity(oLoginIntent);
            }else{
                Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
            }
        }




    }


    //Metodo del API
    private void getUsuario(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://g2c0586b3c2559e-pdc.adb.us-ashburn-1.oraclecloudapps.com/ords/admin/auth/")
                .addConverterFactory(GsonConverterFactory.create())

                .build();
        userAutenticacion oUserAutentication = retrofit.create(userAutenticacion.class);
        Call<usuarioGet> call = oUserAutentication.getUsuario();
        call.enqueue(new Callback<usuarioGet>() {
            @Override
            public void onResponse(Call<usuarioGet> call, Response<usuarioGet> response) {
                if(!response.isSuccessful()){
                    textViewResultado.setText("Codigo error: "+response.code());
                    return;
                }

                Toast.makeText(getApplicationContext(), "se ejecuto correctamente", Toast.LENGTH_SHORT).show();
                usuarioGet obtenerUsuario = response.body();
                String content = "";

                    content = "id"+obtenerUsuario.getId();
                    content = "usuario"+obtenerUsuario.getUsuario();
                    content = "tipo"+obtenerUsuario.getTipo();
                    content = "estado"+obtenerUsuario.getEstado();
                textViewResultado.append(content);

            }

            @Override
            public void onFailure(Call<usuarioGet> call, Throwable t) {

            }
        });
    }

    //Metodo para validar Email
    private boolean validarEmail(String email){
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
}