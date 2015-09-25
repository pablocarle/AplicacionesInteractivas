package com.parking.app.model;

public class AbonoView {
	
	private int idAbono;
	private String nombre;
	private int dias;
	private float descuento;
	
	public AbonoView(Abono abono) {
		super();
		this.idAbono = abono.getIdAbono();
		this.nombre = abono.getNombre();
		this.dias = abono.getDias();
		this.descuento= abono.getDescuento();
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

    @Override
    public String toString() {
        return nombre + " (" + dias + ", " + descuento + "%)";
    }
}
