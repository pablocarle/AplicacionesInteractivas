package com.parking.app.model;

public class Abono {

	private int idAbono;
	private String nombre;
	private int dias;
	private float descuento;
	
	public Abono(String nombre, int dias, float descuento) {
        this.nombre = nombre;
        this.dias = dias;
        this.descuento = descuento;
    }

    public AbonoView obtenerVista() {
		return new AbonoView(this);
	}

	public int getIdAbono() {
		return idAbono;
	}

	public void setIdAbono(int idAbono) {
		this.idAbono = idAbono;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

	public float getDescuento() {
		return descuento;
	}

	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}
}
