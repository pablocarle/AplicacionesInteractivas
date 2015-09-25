package com.parking.app.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import com.parking.app.model.Abono;
import com.parking.app.model.Banco;

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
            return getAbonoId(abono.getNombre());
        } else {
            throw new Exception();
        }
    }

    private int getAbonoId(String nombre) throws Exception {
        Connection conn = PoolConnection.getPoolConnection().getConnection();
        PreparedStatement ps = conn.prepareStatement("select idAbono from abonos where nombre=?");
        ps.setString(1, nombre);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int idAbono = rs.getInt(1);
            PoolConnection.getPoolConnection().releaseConnection(conn);
            return idAbono;
        } else {
            throw new Exception();
        }
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
