package com.parking.app.model;

import java.util.Date;

public class ContratoAbonoCheque extends ContratoAbono {
	
	private String entidadEmisora;
	private Date fechaCobro;
	private int numeroCheque;

	public ContratoAbonoCheque(String entidadEmisora, Date fechaCobro,
			int numeroCheque) {
		super();
		this.entidadEmisora = entidadEmisora;
		this.fechaCobro = fechaCobro;
		this.numeroCheque = numeroCheque;
	}
	
	public String getEntidadEmisora() {
		return entidadEmisora;
	}
	public void setEntidadEmisora(String entidadEmisora) {
		this.entidadEmisora = entidadEmisora;
	}
	public Date getFechaCobro() {
		return fechaCobro;
	}
	public void setFechaCobro(Date fechaCobro) {
		this.fechaCobro = fechaCobro;
	}
	public int getNumeroCheque() {
		return numeroCheque;
	}
	public void setNumeroCheque(int numeroCheque) {
		this.numeroCheque = numeroCheque;
	}
}
