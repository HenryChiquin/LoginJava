package com.example.loginjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Activity_registro extends AppCompatActivity {

    private TextInputEditText emailtxt, passwordtxt;
    private Button btnRegistrarUsuario;
    private FirebaseAuth mAuth;
    private ImageView imgCloseRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        emailtxt = findViewById(R.id.emailTxt);
        passwordtxt = findViewById(R.id.passwordTxt);
        btnRegistrarUsuario = findViewById(R.id.btnRegistrarUser);
        imgCloseRegistro = findViewById(R.id.imgViewCloseRegistro);
        mAuth = FirebaseAuth.getInstance();
        btnRegistrarUsuario.setOnClickListener(View -> createAccount());
        imgCloseRegistro.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), AuthActivity.class));
            FirebaseAuth.getInstance().signOut();
        });

    }

    private void createAccount() {
        String email = emailtxt.getText().toString();
        String password = passwordtxt.getText().toString();
        if (email.isEmpty()) {
            emailtxt.setError("El campo está vacío");
            return;
        }
        if (password.isEmpty()) {
            passwordtxt.setError("El campo está vacío");
            return;
        }
        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(getApplicationContext(),"Se registró correctamente.",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), AuthActivity.class));
                            FirebaseAuth.getInstance().signOut();

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Activity_registro.this, "No se pudo registrar.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}