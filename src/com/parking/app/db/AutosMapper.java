package com.parking.app.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import com.parking.app.model.Auto;
import com.parking.app.model.Banco;

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
		// TODO Auto-generated method stub
	    return 0;
	}

	public void update(Object o) throws Exception {
		// TODO Auto-generated method stub

	}

	public void delete(Object d) throws Exception {
		// TODO Auto-generated method stub

	}

	public Auto select(Object o) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<Auto> selectAll() throws Exception {
        Connection conn = PoolConnection.getPoolConnection().getConnection();
        PreparedStatement ps = conn.prepareStatement("select idAuto, patente, marca, modelo, esgrande from bancos");
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
}
