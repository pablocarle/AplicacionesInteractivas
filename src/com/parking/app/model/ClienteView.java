package com.parking.app.model;

public class ClienteView {

	private int idCliente;
	private String nombre;
	private String email;
	private String telefono;
	
	public ClienteView(Cliente cliente) {
		super();
		this.idCliente = cliente.getIdCliente();
		this.nombre = cliente.getNombre();
		this.email = cliente.getEmail();
		this.telefono = cliente.getTelefono();
	}

}
