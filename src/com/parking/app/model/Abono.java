package com.parking.app.model;

public class Abono {

	private int idAbono;
	private String nombre;
	private int dias;
	private float descuento;
	
	public Abono(String nombre, int dias, float descuento) {
        this.nombre = nombre;
        this.dias = dias;
        this.descuento = descuento;
    }

    public Abono() {
    	super();
	}
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idAbono;
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
		Abono other = (Abono) obj;
		if (idAbono != other.idAbono)
			return false;
		return true;
	}

	public AbonoView obtenerVista() {
		return new AbonoView(this);
	}

	public int getIdAbono() {
		return idAbono;
	}

	public void setIdAbono(int idAbono) {
		this.idAbono = idAbono;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

	public float getDescuento() {
		return descuento;
	}

	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}
}
