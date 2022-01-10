package com.example.loginjava.Api;

import com.example.loginjava.model.Universo;
import com.example.loginjava.model.listadoUniverse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface apiUniverso {
    @GET("categoria")
    Call<listadoUniverse> getUniverso();
}
