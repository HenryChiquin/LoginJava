package com.example.loginjava.Api;

import com.example.loginjava.model.Planetas;
import com.example.loginjava.model.listadoPlanetas;
import com.example.loginjava.model.listadoUniverse;
import com.example.loginjava.model.usuarioGet;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface serviceApi {

    @GET("listado")
    Call<listadoPlanetas> getPlanetas();

    @GET("categoria")
    Call<listadoUniverse> getUniverso();


    @GET("app")
    Call<usuarioGet> getUsuario(@Header("usr") String usr, @Header("pwd") String pwd);

}
