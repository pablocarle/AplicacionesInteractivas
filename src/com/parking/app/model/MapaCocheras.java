package com.parking.app.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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

	/**
	 * Inicializa las cocheras del sistema de acuerdo a la configuracion elegida
	 * 
	 * @param cocheras
	 * @return
	 */
	public Collection<Cochera> inicializar(Collection<Cochera> cocheras, int estandars, int especiales) {
		List<Cochera> retList = new ArrayList<>();
		int simpleCount = 0;
		int especialCount = 0;
		Cochera actual = null;
		Cochera siguiente = null;
		Iterator<Cochera> it = cocheras.iterator();
		while (it.hasNext()) {
			actual = it.next();
			if (simpleCount < estandars) {
				retList.add(actual);
				simpleCount++;
				continue;
			} else {
				if (especialCount < especiales && it.hasNext()) {
					siguiente = it.next();
					retList.add(new CocheraEspezial(actual, siguiente));
					especialCount++;
					continue;
				}
			}
		}
		return retList;
	}
}
