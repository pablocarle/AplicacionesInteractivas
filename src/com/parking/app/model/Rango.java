package com.parking.app.model;

import java.util.Date;

public class Rango {
	
	private Date fechaInicio;
	private Date fechaFin;
	
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public boolean incluye(Date fechaABuscar) {
		return fechaABuscar.after(fechaInicio) && fechaABuscar.before(fechaFin);
	}
}
