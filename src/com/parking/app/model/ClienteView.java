package com.parking.app.model;

import java.util.ArrayList;
import java.util.List;

public class ClienteView {

	private int idCliente;
	private String nombre;
	private String email;
	private String domicilio;
	private String telefono;
	private List<AutoView> autos = new ArrayList<AutoView>();
	
	public ClienteView(Cliente cliente) {
		super();
		this.idCliente = cliente.getIdCliente();
		this.nombre = cliente.getNombre();
		this.email = cliente.getEmail();
		this.domicilio = cliente.getDomicilio();
		this.telefono = cliente.getTelefono();
		this.autos = obtenerAutoViews(cliente);
	}

	private List<AutoView> obtenerAutoViews(Cliente cliente) {
		List<AutoView> retList = new ArrayList<AutoView>();
		for (Auto auto : cliente.getAutos()) {
			retList.add(auto.obtenerVista());
		}
		return retList;
	}
	
	@Override
	public String toString() {
		return nombre + " <" + email + ">";
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public List<AutoView> getAutos() {
		return autos;
	}
	
	public void setAutos(List<AutoView> autos) {
		this.autos = autos;
	}
}
