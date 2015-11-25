package com.parking.app.model;

public abstract class Cochera {
	
	protected int idCochera;
	
	public Cochera() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idCochera;
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
		Cochera other = (Cochera) obj;
		if (idCochera != other.idCochera)
			return false;
		return true;
	}

	public CocheraView obtenerVista() {
		return new CocheraView(this);
	}

	public int getIdCochera() {
		return idCochera;
	}

	public void setIdCochera(int idCochera) {
		this.idCochera = idCochera;
	}
	
	public abstract boolean aceptaAuto(Auto auto);
}
