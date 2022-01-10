package com.example.loginjava.Api;

import com.example.loginjava.model.usuarioGet;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface apiUsuario {
    @GET("app")
    Call<usuarioGet> getUsuario(@Header("usr") String usr, @Header("pwd") String pwd);
}
