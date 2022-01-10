package com.example.loginjava.Api;

import com.example.loginjava.model.Planetas;
import com.example.loginjava.model.usuarioGet;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface serviceApi {


    @GET("app")
    Call<usuarioGet> getUsuario(@Header("usr") String usr, @Header("pwd") String pwd);


    @GET("posts")
    Call<List<Planetas>> getPlanetas();
}
