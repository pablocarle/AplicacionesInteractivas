package com.parking.app.model;

import java.math.BigDecimal;

import com.parking.app.controller.SistemaCocheras;

public class ContratoAbono extends Contrato {

	@Override
	public BigDecimal calcularPrecio() {
		return new BigDecimal((abono.getDias() * 24 * SistemaCocheras.PRECIOHORA * abono.getDescuento()) / 100.0D);
	}
}
