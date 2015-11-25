package com.parking.app.model;

public class Auto {
	
    protected int idAuto;
	protected String patente;
	protected String marca;
	protected String modelo;
	protected boolean esGrande;

	public Auto(String patente, String marca, String modelo, boolean esGrande) {
	    this.patente = patente;
	    this.marca = marca;
	    this.modelo = modelo;
	    this.esGrande = esGrande;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((patente == null) ? 0 : patente.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Auto other = (Auto) obj;
		if (patente == null) {
			if (other.patente != null)
				return false;
		} else if (!patente.equals(other.patente))
			return false;
		return true;
	}

	public int getIdAuto() {
        return idAuto;
    }

	public void setIdAuto(int idAuto) {
	    this.idAuto = idAuto;
	}

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
	public boolean sosGrande() {
        return esGrande;
    }
    public void setGrande(boolean esGrande) {
        this.esGrande = esGrande;
    }
	public AutoView obtenerVista() {
		return new AutoView(this);
	}
}
