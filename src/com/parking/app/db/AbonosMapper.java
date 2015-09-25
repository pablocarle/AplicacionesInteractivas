package com.parking.app.db;

import java.util.Collection;

import com.parking.app.model.Abono;

public class AbonosMapper implements Mapper {
	
	private static AbonosMapper instancia = null;
	
	private AbonosMapper() {
		super();
	}

	public static AbonosMapper obtenerMapper() {
		if (instancia == null) {
			instancia = new AbonosMapper();
		}
		return instancia;
	}
	
	@Override
	public int insert(Object o) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(Object o) throws Exception {

	}

	@Override
	public void delete(Object d) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Abono select(Object o) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<? extends Object> selectAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



}
