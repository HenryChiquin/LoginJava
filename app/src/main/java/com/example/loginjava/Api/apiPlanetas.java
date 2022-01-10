package com.example.loginjava.Api;

import com.example.loginjava.model.Planetas;
import com.example.loginjava.model.listadoPlanetas;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface apiPlanetas {
    @GET("listado")
    Call<listadoPlanetas> getPlanetas();
}
