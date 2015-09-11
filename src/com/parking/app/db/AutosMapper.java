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

	@Override
	public void insert(Object o) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Object o) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Object d) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<Auto> select(Object o) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Auto> selectAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


}
