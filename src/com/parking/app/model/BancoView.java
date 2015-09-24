package com.parking.app.model;

public class BancoView {
    private int idBanco;
    private String nombre;

    public BancoView(Banco banco) {
        this.idBanco = banco.getIdBanco();
        this.nombre = banco.getNombre();
    }

    public int getIdBanco() {
        return this.idBanco;
    }

    public String getNombre() {
        return this.nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }

}
