package com.parking.app.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import com.parking.app.model.LayoutActivo;

public class LayoutActivoMapper implements Mapper {

	@Override
	public int insert(Object o) throws Exception {
		if (o instanceof LayoutActivo) {
			LayoutActivo item = (LayoutActivo) o;
			Connection conn = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement ps = conn.prepareStatement("insert into layoutactivo values (?,?)");
			ps.setInt(1, item.getCocherasEstandar());
			ps.setInt(2, item.getCocherasEspeciales());
			ps.execute();
			PoolConnection.getPoolConnection().releaseConnection(conn);
			return 0;
		} else {
			throw new Exception("Debe ser LayoutActivo: " + o);
		}
	}

	@Override
	public void update(Object o) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Object o) throws Exception {
		if (o instanceof LayoutActivo) {
			LayoutActivo itemDelete = (LayoutActivo) o;
			Connection conn = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement ps = conn.prepareStatement("delete from layoutactivo where cocherasEstandar = ? and cocherasEspeciales = ?");
			ps.setInt(1, itemDelete.getCocherasEstandar());
			ps.setInt(2, itemDelete.getCocherasEspeciales());
			ps.execute();
			PoolConnection.getPoolConnection().releaseConnection(conn);
		} else {
			throw new Exception("Debe ser LayoutActivo	" + o);
		}
	}

	@Override
	public Object select(Object o) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<? extends Object> selectAll() throws Exception {
		Connection conn = PoolConnection.getPoolConnection().getConnection();
		PreparedStatement ps = conn.prepareStatement("select cocherasEstandar, cocherasEspeciales from layoutactivo");
		Collection<LayoutActivo> layoutActivo = new ArrayList<LayoutActivo>();
		if (ps.execute()) {
			ResultSet rs = ps.getResultSet();
			Integer cocherasEstandar;
			Integer cocherasEspeciales;
			LayoutActivo la = null;
			while (rs.next()) {
				cocherasEstandar = rs.getInt(1);
				cocherasEspeciales = rs.getInt(2);
				la = new LayoutActivo();
				la.setCocherasEstandar(cocherasEstandar);
				la.setCocherasEspeciales(cocherasEspeciales);
				layoutActivo.add(la);
			}
		}
		PoolConnection.getPoolConnection().releaseConnection(conn);
		return layoutActivo;
	}

}
