package com.parking.app.controller;

import java.util.Date;
import java.util.List;

import com.parking.app.model.Abono;
import com.parking.app.model.Cliente;
import com.parking.app.model.ClienteView;
import com.parking.app.model.Cochera;
import com.parking.app.model.Contrato;
import com.parking.app.model.ContratoView;
import com.parking.app.model.Tarifa;

public class SistemaCocheras {
	
	private static SistemaCocheras instancia;
	
	private SistemaCocheras() {
		super();
	}
	
	public static SistemaCocheras getSistemaCocheras() {
		if (instancia == null) {
			instancia = new SistemaCocheras();
		}
		return instancia;
	}
	
	private List<Cliente> clientes;
	private List<Contrato> contratos;
	private List<Cochera> cocheras;
	private List<Tarifa> tarifas;
	private List<Abono> abonos;
	
	public ClienteView crearCliente(String nombre, String domicilio, String email, String telefono) throws Exception {
		if (!existeCliente(nombre, email)) {
			Cliente nuevoCliente = new Cliente();
			nuevoCliente.setActivo(true);
			nuevoCliente.setEmail(email);
			nuevoCliente.setNombre(nombre);
			nuevoCliente.setTelefono(telefono);
			clientes.add(nuevoCliente);
			return nuevoCliente.obtenerVista();
		} else {
			throw new Exception("Ya existe cliente con nombre " + nombre + " y email " + email);
		}
	}
	
	private boolean existeCliente(String nombre, String email) {
		// TODO Auto-generated method stub
		return false;
	}

	public ClienteView bajaCliente(int idCliente) {
		return null;
	}
	
	public ClienteView buscarCliente(String nombre, String domicilio, String email, String telefono) {
		return null;
	}
	
	public ClienteView asociarAuto(int idCliente, String patente, String modelo, String marca, boolean esGrande) throws Exception {
		Cliente cliente = obtenerCliente(idCliente);
		if (cliente != null) {
			cliente.asociarAuto(patente, modelo, marca, esGrande);
			ClienteView clienteView = cliente.obtenerVista();
			return clienteView;
		} else {
			throw new Exception("No se encontro cliente con id " + idCliente);
		}
	}
	
	private Cliente obtenerCliente(int idCliente) {
		for (Cliente cliente : clientes) {
			if (cliente.getIdCliente() == idCliente) {
				return cliente;
			}
		}
		return null;
	}

	public ContratoView crearContrato(int idCliente, Date fechaInicio, Date fechaFin, String patente, int idMedioPago) throws Exception {
		return null;
	}
}
