package com.parking.app.model;

public class CocheraEspezial extends Cochera {

	private Cochera cocheraSimple1;
	private Cochera cocheraSimple2;
	
	public CocheraEspezial(Cochera cocheraSimple1, Cochera cocheraSimple2) {
		super();
		this.cocheraSimple1 = cocheraSimple1;
		this.cocheraSimple2 = cocheraSimple2;
	}

	@Override
	public boolean aceptaAuto(Auto auto) {
		// TODO Auto-generated method stub
		return super.aceptaAuto(auto);
	}
	
	public Cochera getCocheraSimple1() {
		return cocheraSimple1;
	}

	public void setCocheraSimple1(Cochera cocheraSimple1) {
		this.cocheraSimple1 = cocheraSimple1;
	}

	public Cochera getCocheraSimple2() {
		return cocheraSimple2;
	}

	public void setCocheraSimple2(Cochera cocheraSimple2) {
		this.cocheraSimple2 = cocheraSimple2;
	}
}
