package com.parking.app.model;


public class CocheraEstandar extends Cochera {

	@Override
	public boolean aceptaAuto(Auto auto) {
		return !auto.sosGrande();
	}

}
