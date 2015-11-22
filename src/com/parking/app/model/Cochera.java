package com.parking.app.model;

public abstract class Cochera {
	
	protected int idCochera;
	
	public Cochera() {
		super();
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

	public boolean aceptaAuto(Auto auto) {
		//TODO Criterio aceptacion auto
		
		return true;
	}
}
