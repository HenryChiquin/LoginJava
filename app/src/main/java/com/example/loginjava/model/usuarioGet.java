package com.example.loginjava.model;

import com.google.gson.annotations.SerializedName;

public class usuarioGet {

    private int id;
    private String usuario;
    private int tipo;
    private int estado;


    @SerializedName("body")
    private String text;


    public int getId() {
        return id;
    }

    public String getUsuario() {
        return usuario;
    }

    public int getTipo() {
        return tipo;
    }

    public int getEstado() {
        return estado;
    }

    public String getText() {
        return text;
    }
}
