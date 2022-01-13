package com.example.loginjava.Util;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class conectionRetrofit {
    private static Retrofit retrofit = null;

    public static Retrofit getConection(String urlApi){
        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(urlApi).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }


}
