package com.parking.app.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
	    if (o instanceof Abono) {
            Abono abono = (Abono) o;
            Connection conn = PoolConnection.getPoolConnection().getConnection();
            PreparedStatement ps = conn.prepareStatement("insert into abonos (nombre, dias, descuento) values (?, ?, ?)");
            ps.setString(1, abono.getNombre());
            ps.setInt(2, abono.getDias());
            ps.setFloat(3, abono.getDescuento());
            ps.execute();
            PoolConnection.getPoolConnection().releaseConnection(conn);
            return selectPorDia(abono.getDias()).getIdAbono();
        } else {
            throw new Exception();
        }
    }

	@SuppressWarnings("unused")
	private int getAbonoId(String nombre) throws Exception {
        Connection conn = PoolConnection.getPoolConnection().getConnection();
        PreparedStatement ps = conn.prepareStatement("select idAbono from abonos where nombre=?");
        ps.setString(1, nombre);
        ResultSet rs = ps.executeQuery();
        try {
			if (rs.next()) {
				int idAbono = rs.getInt(1);
				PoolConnection.getPoolConnection().releaseConnection(conn);
				return idAbono;
			} else {
				throw new Exception();
			}
		} finally {
			PoolConnection.getPoolConnection().releaseConnection(conn);
		}
    }


	@Override
	public void update(Object o) throws Exception {

	}

	@Override
	public void delete(Object d) throws Exception {
		int idAbono = 0;
		if (d instanceof Number) {
			idAbono = ((Number) d).intValue();
		} else if (d instanceof Abono) {
			idAbono = ((Abono) d).getIdAbono();
		} else {
			throw new Exception("No se reconoce " + d);
		}
		Connection conn = PoolConnection.getPoolConnection().getConnection();
		PreparedStatement ps = conn.prepareStatement("delete from abonos where idAbono = ?");
		ps.setInt(1, idAbono);
		ps.executeUpdate();
		PoolConnection.getPoolConnection().releaseConnection(conn);
	}

	@Override
	public Abono select(Object o) throws Exception {
		int idAbono = 0;
		if (o instanceof Number) {
			idAbono = ((Number) o).intValue();
		}
		Connection conn = PoolConnection.getPoolConnection().getConnection();
		PreparedStatement ps = conn.prepareStatement("select idAbono, nombre, dias, descuento from abonos where idAbono = ?");
		ps.setInt(1, idAbono);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			Abono abono = new Abono();
			abono.setIdAbono(rs.getInt(1));
			abono.setNombre(rs.getString(2));
			abono.setDias(rs.getInt(3));
			abono.setDescuento(rs.getFloat(4));
			return abono;
		}
		PoolConnection.getPoolConnection().releaseConnection(conn);
		return null;
	}

    private Abono selectPorDia(int dias) throws SQLException {
		Connection conn = PoolConnection.getPoolConnection().getConnection();
		PreparedStatement ps = conn.prepareStatement("select idAbono, nombre, dias, descuento from abonos where dias = ?");
		ps.setInt(1, dias);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			Abono abono = new Abono();
			abono.setIdAbono(rs.getInt(1));
			abono.setNombre(rs.getString(2));
			abono.setDias(rs.getInt(3));
			abono.setDescuento(rs.getFloat(4));
			return abono;
		}
		PoolConnection.getPoolConnection().releaseConnection(conn);
		return null;
	}
	
	@Override
	public Collection<Abono> selectAll() throws Exception {
	    Connection conn = PoolConnection.getPoolConnection().getConnection();
        PreparedStatement ps = conn.prepareStatement("select idAbono, nombre, dias, descuento from abonos");
        Collection<Abono> abonos = new ArrayList<Abono>();

        if (ps.execute()) {
            ResultSet rs = ps.getResultSet();
            int idAbono;
            String nombre;
            int dias;
            float descuento;

            Abono abono = null;
            while (rs.next()) {
                idAbono = rs.getInt(1);
                nombre = rs.getString(2);
                dias = rs.getInt(3);
                descuento = rs.getFloat(4);
                abono = new Abono(nombre, dias, descuento);
                abono.setIdAbono(idAbono);
                abonos.add(abono);
            }
        }
        PoolConnection.getPoolConnection().releaseConnection(conn);
        return abonos;
	}
}
