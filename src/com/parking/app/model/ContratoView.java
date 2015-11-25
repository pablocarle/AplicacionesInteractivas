package com.parking.app.model;

import java.math.BigDecimal;
import java.util.Date;

public class ContratoView {

	private Date fechaInicio;
	private ClienteView cliente;
	private AutoView auto;
	private CocheraView cochera;
	private MedioPagoView medioPago;
	private AbonoView abono;
	private BigDecimal precio;
	private int idContrato;
	private boolean activo;
	
	public ContratoView(Contrato contrato) {
		super();
		this.fechaInicio = contrato.getFechaInicio();
		this.cliente = contrato.getCliente().obtenerVista();
		this.auto = contrato.getAuto().obtenerVista();
		this.cochera = contrato.getCochera() != null ? contrato.getCochera().obtenerVista() : null;
		this.medioPago = contrato.getMedioPago() != null ? contrato.getMedioPago().obtenerVista() : null;
		this.abono = contrato.getAbono() != null ? contrato.getAbono().obtenerVista() : null;
		this.idContrato = contrato.getIdContrato();
		this.activo = contrato.isActivo();
	}

	@Override
	public String toString() {
		return "[Fecha de Inicio: " + fechaInicio + ", Cliente: "
				+ cliente + ", Auto: " + auto + ", Medio de Pago: " + medioPago
				+ ", Abono: " + abono + ", Precio: " + precio + "]";
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public ClienteView getCliente() {
		return cliente;
	}

	public AutoView getAuto() {
		return auto;
	}

	public CocheraView getCochera() {
		return cochera;
	}

	public MedioPagoView getMedioPago() {
		return medioPago;
	}

	public AbonoView getAbono() {
		return abono;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public int getIdContrato() {
		return idContrato;
	}

	public boolean isActivo() {
		return activo;
	}
}
