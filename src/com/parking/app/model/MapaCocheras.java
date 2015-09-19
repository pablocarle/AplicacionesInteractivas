package com.parking.app.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MapaCocheras {
	
	private Map<Cochera, Auto> mapa = new HashMap<Cochera, Auto>();
	
	public boolean hayDisponible(Auto auto) {
		for (Map.Entry<Cochera, Auto> entry : mapa.entrySet()) {
			if (entry.getKey().aceptaAuto(auto)) {
				return true;
			}
		}
		return false;
	}

	public void inicializar(Collection<Cochera> cocheras) {
		for (Cochera cochera : cocheras) {
			mapa.put(cochera, null);
		}
	}
}
