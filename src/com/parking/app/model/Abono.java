package com.parking.app.model;

public class Abono {

	private int idAbono;
	private String nombre;
	private String descripcion;
	
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
