package com.parking.app.model;

public class AutoView {
    private int idAuto;
	private String patente;
	private String modelo;
	private String marca;
	private boolean esGrande;

	public AutoView(Auto auto) {
	    this.idAuto = auto.getIdAuto();
		this.patente = auto.getPatente();
		this.modelo = auto.getModelo();
		this.marca = auto.getMarca();
		this.esGrande = auto.sosGrande();
	}

	@Override
	public String toString() {
	    String str = patente + " - " + marca + " " + modelo;
	    if (esGrande) {
	        str += " (grande)"; 
	    }
		return  str;
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

    public boolean sosGrande() {
        return esGrande;
    }

    public void setGrande(boolean esGrande) {
        this.esGrande = esGrande;
    }

    public int getIdAuto() {
        return idAuto;
    }
}
