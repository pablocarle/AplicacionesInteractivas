package com.parking.app.model;

public class MedioPago {
    private int idMedioPago;
    private String nombre;
    private Banco banco;
    private String descripcion;
    private String ftpOut;
    private String ftpIn;
    private String archivo;

	public MedioPago(String nombre, Banco banco, String descripcion,
            String ftpOut, String ftpIn, String archivo) {
        this.nombre = nombre;
        this.banco = banco;
        this.descripcion = descripcion;
        this.ftpOut = ftpOut;
        this.ftpIn = ftpIn;
        this.archivo = archivo;
    }

    public MedioPagoView obtenerVista() {
		return new MedioPagoView(this);
	}

    public String getNombre() {
        return nombre;
    }

    public Banco getBanco() {
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

    public int getIdMedioPago() {
        return idMedioPago;
    }

    public void setIdMedioPago(int idMedioPago) {
        this.idMedioPago = idMedioPago;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFtpOut(String ftpOut) {
        this.ftpOut = ftpOut;
    }

    public void setFtpIn(String ftpIn) {
        this.ftpIn = ftpIn;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

}
