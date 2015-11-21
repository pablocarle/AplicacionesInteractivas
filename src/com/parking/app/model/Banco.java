package com.parking.app.model;

public class Banco {
	
    private int idBanco;
    private String nombre;
    private String ftpIn;
    private String ftpOut;
    
    public Banco(String nombre, String ftpIn, String ftpOut) {
        this.nombre = nombre;
        this.ftpIn = ftpIn;
        this.ftpOut = ftpOut;
    }

    public Banco(int idBanco, String nombre, String ftpIn, String ftpOut) {
        this(nombre, ftpIn, ftpOut);
        this.idBanco = idBanco;
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

    public void setIdAbono(int idBanco) {
        this.idBanco = idBanco;
    }

    public String getFtpIn() {
        return ftpIn;
    }
    
    public String getFtpOut() {
        return ftpOut;
    }
}
