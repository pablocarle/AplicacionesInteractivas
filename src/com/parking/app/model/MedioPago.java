package com.parking.app.model;

public class MedioPago {
	
    private int idMedioPago;
    private String nombre;
    private Banco banco;
    private String descripcion;

	public MedioPago(String nombre, Banco banco, String descripcion) {
        this.nombre = nombre;
        this.banco = banco;
        this.descripcion = descripcion;
    }

    public MedioPagoView obtenerVista() {
		return new MedioPagoView(this);
	}

    public boolean esEfectivo() {
    	return banco == null;
    }
    
    public String getNombre() {
        return nombre;
    }

    public Banco getBanco() {
        return banco;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getIdMedioPago() {
        return idMedioPago;
    }

    public void setIdMedioPago(int idMedioPago) {
        this.idMedioPago = idMedioPago;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

	public boolean isTarjeta() {
		// TODO Auto-generated method stub
		return false;
	}
}
