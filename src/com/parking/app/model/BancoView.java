package com.parking.app.model;

public class BancoView {
    private int idBanco;
    private String nombre;
    private Banco banco;

    public BancoView(Banco banco) {
        this.idBanco = banco.getIdBanco();
        this.nombre = banco.getNombre();
        this.banco = banco;
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

    public Banco getBanco() {
        return banco;
    }
}
