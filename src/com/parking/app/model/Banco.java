package com.parking.app.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Banco {
	
    private int idBanco;
    private String nombre;

    private List<ReporteCobros> clientesAReportar = new ArrayList<ReporteCobros>();
    
    public Banco(int idBanco, String nombre) {
        this.idBanco = idBanco;
        this.nombre = nombre;
    }
    
    public BancoView obtenerVista() {
        return new BancoView(this);
    }

    public int getIdBanco() {
        return this.idBanco;
    }

    public String getNombre() {
        return this.nombre;
    }
    
    public void registrar(ReporteCobros reporte) {
    	if (!clientesAReportar.contains(reporte)) {
    		clientesAReportar.add(reporte);
    	}
    }
    
    public void efectuarCobros() {
//    	TODO Generar archivo
    	EventoCobro ec = null;
    	for (ReporteCobros rc : clientesAReportar) {
    		ec = new EventoCobro(nombre + "_" + (new Date()).toString() + ".txt");
    		rc.cobroEfectuado(ec);
    	}
    }
}
