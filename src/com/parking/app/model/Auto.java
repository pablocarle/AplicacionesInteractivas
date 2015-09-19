package com.parking.app.model;

public class Auto {

	protected String patente;
	protected String marca;
	protected String modelo;

	public String getPatente() {
		return patente;
	}
	public void setPatente(String patente) {
		this.patente = patente;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public AutoView obtenerVista() {
		return new AutoView(this);
	}
}
