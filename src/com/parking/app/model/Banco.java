package com.parking.app.model;

public class Banco {
    private int idBanco;
    private String nombre;

    public Banco(int idBanco, String nombre) {
        this.idBanco = idBanco;
        this.nombre = nombre;
    }
    
    public BancoView obtenerVista() {
        return new BancoView(this);
    }

    public int getIdBanco() {
        return this.idBanco;
    }

    public String getNombre() {
        return this.nombre;
    }
}
