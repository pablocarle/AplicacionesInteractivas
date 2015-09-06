package com.parking.app.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class MapaCocheras {
	
	private Map<Integer, Map<Auto, List<Rango>>> map;
	
	public boolean hayDisponible(Date fecha) {
		Map<Auto, List<Rango>> autos;
		for (Map.Entry<Integer, Map<Auto, List<Rango>>> entry : map.entrySet()) {
			autos = entry.getValue();
			
		}
		return false;
	}
	
	private boolean incluidoEnRangos(List<Rango> rangos, Date fechaABuscar) {
		for (Rango rango : rangos) {
			if (rango.incluye(fechaABuscar))
				return true;
		}
		return false;
	}
}
