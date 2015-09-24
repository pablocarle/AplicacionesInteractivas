package com.parking.app.model;

public class MedioPagoView {
    private int idMedioPago;
    private String nombre;
    private BancoView banco;
    private String descripcion;
    private String ftpOut;
    private String ftpIn;
    private String archivo;

	public MedioPagoView(MedioPago medioPago) {
	    idMedioPago = medioPago.getIdMedioPago();
        nombre = medioPago.getNombre();
        banco = medioPago.getBanco().obtenerVista();
        descripcion = medioPago.getDescripcion();
        ftpOut = medioPago.getFtpOut();
        ftpIn = medioPago.getFtpIn();
        archivo = medioPago.getArchivo();
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

    public String getFtpOut() {
        return ftpOut;
    }

    public String getFtpIn() {
        return ftpIn;
    }

    public String getArchivo() {
        return archivo;
    }
}
