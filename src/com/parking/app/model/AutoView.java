package com.parking.app.model;

public class AutoView {

	private String patente;
	private String modelo;
	private String marca;
	
	public AutoView(Auto auto) {
		this.patente = auto.getPatente();
		this.modelo = auto.getModelo();
		this.marca = auto.getMarca();
	}

	@Override
	public String toString() {
		return patente + "(" + marca + " - " + modelo + ")";
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}
}
