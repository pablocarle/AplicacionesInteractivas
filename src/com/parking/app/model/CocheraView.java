package com.parking.app.model;

public class CocheraView {

	private int idCochera;
	
	public CocheraView(Cochera cochera) {
		super();
		this.idCochera = cochera.getIdCochera();
	}

	public int getIdCochera() {
		return idCochera;
	}
}
