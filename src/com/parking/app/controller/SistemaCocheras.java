package com.parking.app.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Vector;

import com.parking.app.db.ClienteMapper;
import com.parking.app.db.ContratosMapper;
import com.parking.app.db.TarifasMapper;
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
	
	private void cargarModelo() throws Exception {
		clientes = ClienteMapper.obtenerMapper().selectAll();
//		autos = AutosMapper.obtenerMapper().selectAll();
//		tarifas = TarifasMapper.obtenerMapper().selectAll();
//		contratos = ContratosMapper.obtenerMapper().selectAll();
	}

	public static SistemaCocheras getSistemaCocheras() {
		if (instancia == null) {
			instancia = new SistemaCocheras();
		}
		return instancia;
	}
	
	private Collection<Cliente> clientes;
	private Collection<Contrato> contratos;
	private Collection<Cochera> cocheras;
	private Collection<Tarifa> tarifas;
	private Collection<Abono> abonos;
	
	{
		try {
			cargarModelo();
		} catch (Exception e) {
			System.out.println("error en carga de modelo. " + e);
			e.printStackTrace();
		}
	}
	
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
		return false;
	}

	public ClienteView bajaCliente(int idCliente) throws Exception {
		Cliente cliente = obtenerCliente(idCliente);
		if (cliente == null)
			throw new Exception("No se encontro cliente con id " + idCliente);
		cliente.setActivo(false);
		//TODO persistir
		return cliente.obtenerVista();
	}
	
	public ClienteView buscarCliente(String nombre, String domicilio, String email, String telefono) {
		return null;
	}
	
	public Vector<ClienteView> listarClientes() {
		Vector<ClienteView> lista = new Vector<ClienteView>();
		for (Cliente cliente : clientes) {
			lista.add(cliente.obtenerVista());
		}
		return lista;
		
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
