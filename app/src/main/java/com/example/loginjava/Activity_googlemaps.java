package com.example.loginjava;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Activity_googlemaps extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap map;
    private ImageView imgclosMaps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_googlemaps);

        imgclosMaps = findViewById(R.id.imageViewMapa);
        imgclosMaps.setOnClickListener(view -> {
            onBackPressed();
        });
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapFragmento);

        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        LatLng cobanAV = new LatLng(15.471963828229702, -90.38500210713157);
        map.addMarker(new MarkerOptions().position(cobanAV).title("Coban Alta Verapaz"));
        map.moveCamera(CameraUpdateFactory.newLatLng(cobanAV));
    }
}