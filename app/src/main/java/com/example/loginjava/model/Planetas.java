package com.example.loginjava.model;

public class Planetas {
    private int id;
    private String nombre;
    private int tipo;
    private int estado;
    private String imagen;




    //GETTER
    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public int getTipo() {
        return tipo;
    }
    public int getEstado() {
        return estado;
    }
    public String getImagen() {
        return imagen;
    }
    //SETTER
    public void setId(int id) { this.id = id; }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    public void setEstado(int estado) {
        this.estado = estado;
    }
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
