package com.parking.app.model;

public class MedioPagoView {

	private int idMedioPago;
    private String nombre;
    private BancoView banco;
    private String descripcion;
    private String ftpOut;
    private String ftpIn;
    private String archivo;
    private boolean esTarjeta;

	public MedioPagoView(MedioPago medioPago) {
		super();
	    idMedioPago = medioPago.getIdMedioPago();
        nombre = medioPago.getNombre();
        banco = (medioPago.getBanco() == null) ? null : medioPago.getBanco().obtenerVista();
        descripcion = medioPago.getDescripcion();
        esTarjeta = medioPago.isTarjeta();
    }

    public MedioPagoView() {
    	super();
	}

	public int getIdMedioPago() {
		return idMedioPago;
	}

    public String getNombre() {
        return nombre;
    }

    public BancoView getBanco() {
        return banco;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return nombre +" ("+descripcion+")";
    }

	public String getFtpOut() {
		return ftpOut;
	}

	public void setFtpOut(String ftpOut) {
		this.ftpOut = ftpOut;
	}

	public String getFtpIn() {
		return ftpIn;
	}

	public void setFtpIn(String ftpIn) {
		this.ftpIn = ftpIn;
	}

	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	public void setIdMedioPago(int idMedioPago) {
		this.idMedioPago = idMedioPago;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setBanco(BancoView banco) {
		this.banco = banco;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isEsTarjeta() {
		return esTarjeta;
	}

	public void setEsTarjeta(boolean esTarjeta) {
		this.esTarjeta = esTarjeta;
	}
}
