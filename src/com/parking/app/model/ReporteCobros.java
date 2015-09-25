package com.parking.app.model;

public abstract class ReporteCobros {

	private int field;
	
	public abstract void cobroEfectuado(EventoCobro e);

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + field;
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
		ReporteCobros other = (ReporteCobros) obj;
		if (field != other.field)
			return false;
		return true;
	}
}
