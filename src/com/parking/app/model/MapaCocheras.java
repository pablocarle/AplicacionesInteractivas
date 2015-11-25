package com.parking.app.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.parking.app.db.LayoutActivoMapper;

public class MapaCocheras {
	
	private Map<Cochera, Auto> mapa = new HashMap<Cochera, Auto>();
	
	public boolean hayDisponible(Auto auto) {
		for (Map.Entry<Cochera, Auto> entry : mapa.entrySet()) {
			if (entry.getValue() == null && entry.getKey().aceptaAuto(auto)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean liberar(Auto auto) {
		if (mapa.containsKey(auto)) {
			mapa.remove(auto);
			return true;
		} else {
			return false;
		}
	}
	
	public Cochera asignar(Auto auto) throws Exception {
		if (hayDisponible(auto)) {
			for (Map.Entry<Cochera, Auto> entry : mapa.entrySet()) {
				if (entry.getValue() == null && entry.getKey().aceptaAuto(auto)) {
					return entry.getKey();
				}
			}
			throw new Exception("No hay disponibilidad para el auto " + auto);
		} else {
			throw new Exception("No hay disponibilidad para el auto " + auto);
		}
	}

	/**
	 * Inicializa las cocheras del sistema de acuerdo a la configuracion elegida
	 * 
	 * @param cocheras
	 * @return
	 * @throws Exception 
	 */
	public Collection<Cochera> inicializar(Collection<Cochera> cocheras, int estandars, int especiales) throws Exception {
		limpiarLayoutActivos();
		List<Cochera> retList = new ArrayList<Cochera>();
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
		for (Cochera cochera : retList) {
			if (!mapa.containsKey(cochera)) {
				mapa.put(cochera, null);
			}
			mapa.put(cochera, null);
		}
		LayoutActivo layout = new LayoutActivo();
		layout.setCocherasEstandar(estandars);
		layout.setCocherasEspeciales(especiales);
		LayoutActivoMapper.getMapper().insert(layout);
		return retList;
	}

	private void limpiarLayoutActivos() throws Exception {
		Collection<LayoutActivo> layoutActivos = LayoutActivoMapper.getMapper().selectAll();
		for (LayoutActivo la : layoutActivos) {
			LayoutActivoMapper.getMapper().delete(la);
		}
	}
}
