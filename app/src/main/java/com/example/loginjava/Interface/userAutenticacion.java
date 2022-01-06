package com.example.loginjava.Interface;

import com.example.loginjava.model.usuarioGet;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface userAutenticacion {

    @GET("app")
    Call<usuarioGet> getUsuario(@Header("usr") String usr,@Header("pwd") String pwd);

}
