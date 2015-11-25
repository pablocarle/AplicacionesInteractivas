package com.parking.app.model;

import java.math.BigDecimal;

import com.parking.app.controller.SistemaCocheras;

public class ContratoHora extends Contrato {

	private int horas;
	
	public ContratoHora(int horas) {
		super();
		this.horas = horas;
	}
	
	@Override
	public BigDecimal calcularPrecio() {
		return new BigDecimal(SistemaCocheras.PRECIOHORA * horas ); 
	}
}
