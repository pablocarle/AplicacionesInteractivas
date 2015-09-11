package com.parking.app.model;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
	
	private int idCliente;
	private String nombre;
	private String telefono;
	private String email;
	private boolean activo;
	private List<Auto> autos = new ArrayList<Auto>();
	private String domicilio;
	
	public Cliente() {
		super();
	}
	
	public Cliente(int idCliente, String nombre, String domicilio, String telefono,
			String email, boolean activo) {
		super();
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
		this.activo = activo;
		this.domicilio = domicilio;
	}
	
	public ClienteView obtenerVista() {
		return new ClienteView(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idCliente;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		Cliente other = (Cliente) obj;
		if (idCliente != other.idCliente)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
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
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public void setAutos(List<Auto> autos) {
		this.autos = autos;
	}

	public AutoView asociarAuto(String patente, String modelo, String marca, boolean esGrande) throws Exception {
		Auto nuevoAuto = null;
		if (esGrande) {
			
			nuevoAuto = new AutoPickUpOCamionPequeno();
		} else {
			nuevoAuto = new AutoSedan();
		}
		nuevoAuto.setPatente(patente);
		nuevoAuto.setMarca(marca);
		nuevoAuto.setModelo(modelo);
		if (!autos.contains(nuevoAuto)) {
			autos.add(nuevoAuto);
			return nuevoAuto.obtenerVista();
		} else {
			throw new Exception("El auto " + nuevoAuto.getPatente() + " ya esta asociado al cliente");
		}
	}

	public List<Auto> getAutos() {
		return autos;
	}
}
