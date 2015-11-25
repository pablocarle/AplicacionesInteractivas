package com.parking.app.model;

import java.util.ArrayList;
import java.util.List;

public class ContratoAbonoCheque extends ContratoAbono {

	private List<Cheque> cheques = new ArrayList<Cheque>();
	
	public ContratoAbonoCheque(List<?> cheques) {
		super();
		for (Object object : cheques) {
			if (object instanceof Cheque) {
				this.cheques.add((Cheque)object);
			} else if (object instanceof ChequeView) {
				this.cheques.add(convertir((ChequeView)object));
			}
		}
	}

	private Cheque convertir(ChequeView object) {
		Cheque cheque = new Cheque();
		cheque.setEntidad(object.getEntidad());
		cheque.setFecha(object.getFecha());
		cheque.setMonto(object.getMonto());
		cheque.setNumero(object.getNumero());
		return cheque;
	}

	public List<Cheque> getCheques() {
		return cheques;
	}

	public void setCheques(List<Cheque> cheques) {
		this.cheques = cheques;
	}
}
