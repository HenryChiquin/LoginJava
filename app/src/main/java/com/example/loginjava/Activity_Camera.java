package com.example.loginjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Activity_Camera extends AppCompatActivity {

    private ImageButton imgBtnCamera,imgBtnGaleria,btnregresar;
    private ImageView imgViewPrevio;
    String rutaImagen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        imgBtnCamera = findViewById(R.id.imgbtnCamera);
        imgBtnGaleria = findViewById(R.id.imgBtnGaleria);
        imgViewPrevio = findViewById(R.id.imgViewPrevio);

        btnregresar = findViewById(R.id.imgBtnRegresar);
        btnregresar.setOnClickListener(view -> {
            onBackPressed();
        });


        imgBtnCamera.setOnClickListener(View -> tomarFoto());
        imgBtnGaleria.setOnClickListener(View -> galeriaFotos());

    }

    final int IMAGE_CAPTURE = 1;
    private void tomarFoto() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePictureIntent, IMAGE_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,@NonNull Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imgViewPrevio.setImageBitmap(imageBitmap);
            try {
                FileOutputStream fos = openFileOutput(crearNombreArchivoJPEG(), Context.MODE_PRIVATE);
                imageBitmap.compress(Bitmap.CompressFormat.JPEG,100,fos);
                fos.close();
            }catch (Exception e){

            }
        }
    }


    private String crearNombreArchivoJPEG() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return timeStamp+".jpg";
    }
    public void galeriaFotos(){
        Intent intent = new Intent(this,Activity_galeria.class);
        startActivity(intent);
    }

}