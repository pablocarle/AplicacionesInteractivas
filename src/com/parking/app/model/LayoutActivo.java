package com.parking.app.model;

public class LayoutActivo {
	
	private int cocherasEstandar;
	private Integer cocherasEspeciales;
	
	public LayoutActivo() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((cocherasEspeciales == null) ? 0 : cocherasEspeciales
						.hashCode());
		result = prime * result + cocherasEstandar;
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
		LayoutActivo other = (LayoutActivo) obj;
		if (cocherasEspeciales == null) {
			if (other.cocherasEspeciales != null)
				return false;
		} else if (!cocherasEspeciales.equals(other.cocherasEspeciales))
			return false;
		if (cocherasEstandar != other.cocherasEstandar)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LayoutActivo [cocherasEstandar=" + cocherasEstandar
				+ ", cocherasEspeciales=" + cocherasEspeciales + "]";
	}

	public int getCocherasEstandar() {
		return cocherasEstandar;
	}

	public void setCocherasEstandar(int cocherasEstandar) {
		this.cocherasEstandar = cocherasEstandar;
	}

	public Integer getCocherasEspeciales() {
		return cocherasEspeciales;
	}

	public void setCocherasEspeciales(Integer cocherasEspeciales) {
		this.cocherasEspeciales = cocherasEspeciales;
	}
}
