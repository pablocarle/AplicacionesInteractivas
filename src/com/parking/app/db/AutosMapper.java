package com.parking.app.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.parking.app.model.Auto;

public class AutosMapper implements Mapper {

	private static AutosMapper instancia = null;
	
	private AutosMapper() {
		super();
	}
	
	public static AutosMapper obtenerMapper() {
		if (instancia == null) {
			instancia = new AutosMapper();
		}
		return instancia;
	}
	
	public int insert(Object o) throws Exception {
      if (o instanceof Auto) {
            Auto auto = (Auto) o;
            Connection conn = PoolConnection.getPoolConnection().getConnection();
            PreparedStatement ps = conn.prepareStatement("insert into autos (patente, marca, modelo, esgrande) values (?, ?, ?, ?)");
            ps.setString(1, auto.getPatente());
            ps.setString(2, auto.getMarca());
            ps.setString(3, auto.getModelo());
            ps.setBoolean(4, auto.sosGrande());
            ps.execute();

            PoolConnection.getPoolConnection().releaseConnection(conn);
            return getAutoId(auto.getPatente());
        } else {
            throw new Exception();
        }
	}

	private int getAutoId(String patente) throws SQLException {
        Connection conn = PoolConnection.getPoolConnection().getConnection();
        PreparedStatement ps = conn.prepareStatement("select idAuto from autos where patente=?");
        ps.setString(1, patente);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int idAuto = rs.getInt(1);
            PoolConnection.getPoolConnection().releaseConnection(conn);
            return idAuto;
        } else {
            throw new SQLException();
        }
    }

    public void update(Object o) throws Exception {
		// TODO Update

	}

	public void delete(Object d) throws Exception {
		int id = 0;
		if (d instanceof Number) {
			id = ((Number) d).intValue();
		}
		Connection conn = PoolConnection.getPoolConnection().getConnection();
		PreparedStatement ps = conn.prepareStatement("delete from autos where idAuto = ?");
		ps.setInt(1, id);
		ps.execute();
		PoolConnection.getPoolConnection().releaseConnection(conn);
	}

	public Auto select(Object o) throws Exception {
		int id = 0;
		if (o instanceof Number) {
			id = ((Number) o).intValue();
		}
		Connection conn = PoolConnection.getPoolConnection().getConnection();
		PreparedStatement ps = conn.prepareStatement("select idAuto, patente, marca, modelo, esgrande from autos where idAuto = ?");
		ps.setInt(1, id);
		if (ps.execute()) {
			ResultSet rs = ps.getResultSet();
			if (rs.next()) {
				int idAuto = rs.getInt(1);
				String patente = rs.getString(2);
				String marca = rs.getString(3);
				String modelo = rs.getString(4);
				boolean esGrande = rs.getBoolean(5);
				Auto auto = new Auto(patente, marca, modelo, esGrande);
				auto.setIdAuto(idAuto);
				PoolConnection.getPoolConnection().releaseConnection(conn);
				return auto;
			}
		}
		return null;
	}

	public Collection<Auto> selectAll() throws Exception {
        Connection conn = PoolConnection.getPoolConnection().getConnection();
        PreparedStatement ps = conn.prepareStatement("select idAuto, patente, marca, modelo, esgrande from autos");
        Collection<Auto> autos = new ArrayList<Auto>();

        if (ps.execute()) {
            ResultSet rs = ps.getResultSet();
            int idAuto;
            String patente;
            String marca;
            String modelo;
            boolean esGrande;

            Auto auto = null;
            while (rs.next()) {
                idAuto = rs.getInt(1);
                patente= rs.getString(2);
                marca = rs.getString(3);
                modelo = rs.getString(4);
                esGrande = rs.getBoolean(5);
                auto = new Auto(patente, marca, modelo, esGrande);
                auto.setIdAuto(idAuto);
                autos.add(auto);
            }
        }
        PoolConnection.getPoolConnection().releaseConnection(conn);
        return autos;
	}

    public void asociarAutoCliente(int idAuto, int idCliente) throws SQLException {
        Connection conn = PoolConnection.getPoolConnection().getConnection();
        PreparedStatement ps = conn.prepareStatement("insert into clientes_autos (idCliente, idAuto) values (?, ?)");
        ps.setInt(1, idCliente);
        ps.setInt(2, idAuto);
        ps.execute();

        PoolConnection.getPoolConnection().releaseConnection(conn);
    }

	public List<Auto> selectDeCliente(int idCliente) throws Exception {
		Connection conn = PoolConnection.getPoolConnection().getConnection();
        PreparedStatement ps = conn.prepareStatement("select idCliente, idAuto from clientes_autos where idCliente = ?");
        ps.setInt(1, idCliente);
        List<Auto> autos = new ArrayList<Auto>();

        if (ps.execute()) {
            ResultSet rs = ps.getResultSet();
            int idAuto;

            Auto auto = null;
            while (rs.next()) {
                idAuto = rs.getInt(2);
                auto = select(idAuto);
                if (auto != null) {
                	autos.add(auto);
                }
            }
        }
        PoolConnection.getPoolConnection().releaseConnection(conn);
        return autos;
	}
}
