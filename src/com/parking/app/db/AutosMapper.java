package com.parking.app.db;

import java.util.Collection;

import com.parking.app.model.Auto;

public class AutosMapper implements Mapper {
	
	private static AutosMapper instancia = null;
	
	public static AutosMapper obtenerMapper() {
		if (instancia == null) {
			instancia = new AutosMapper();
		}
		return instancia;
	}
	
	private AutosMapper() {
		super();
	}

	public int insert(Object o) throws Exception {
		// TODO Auto-generated method stub
	    return 0;
	}

	public void update(Object o) throws Exception {
		// TODO Auto-generated method stub

	}

	public void delete(Object d) throws Exception {
		// TODO Auto-generated method stub

	}

	public Collection<Auto> select(Object o) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<Auto> selectAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
