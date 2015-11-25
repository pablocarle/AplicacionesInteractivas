package com.parking.app.model;

import java.math.BigDecimal;
import java.util.Date;

public class ChequeView {
	
	private String entidad;
	private Date fecha;
	private int idContrato;
	private BigDecimal monto;
	private String numero;

	public ChequeView() {
		super();
	}
	
	public ChequeView(Cheque cheque) {
		super();
		this.entidad = cheque.getEntidad();
		this.fecha = cheque.getFecha();
		this.idContrato = cheque.getIdContrato();
		this.monto = cheque.getMonto();
		this.numero = cheque.getNumero();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((entidad == null) ? 0 : entidad.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
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
		ChequeView other = (ChequeView) obj;
		if (entidad == null) {
			if (other.entidad != null)
				return false;
		} else if (!entidad.equals(other.entidad))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[entidad=" + entidad + ", fecha=" + fecha
				+ ", monto=" + monto + ", numero=" + numero + "]";
	}

	public String getEntidad() {
		return entidad;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(int idContrato) {
		this.idContrato = idContrato;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
}
