package com.parking.app.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import com.parking.app.model.Banco;


public class BancoMapper implements Mapper {
    
    private static BancoMapper instancia = null;
    
    private BancoMapper() {
        super();
    }
    
    public static BancoMapper obtenerMapper() {
        if (instancia == null) {
            instancia = new BancoMapper();
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
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(Object d) throws Exception {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Collection<? extends Object> select(Object o) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<Banco> selectAll() throws Exception {
        Connection conn = PoolConnection.getPoolConnection().getConnection();
        PreparedStatement ps = conn.prepareStatement("select idBanco, nombre from bancos");
        Collection<Banco> bancos = new ArrayList<Banco>();

        if (ps.execute()) {
            ResultSet rs = ps.getResultSet();
            int idBanco;
            String nombre;

            Banco banco = null;
            while (rs.next()) {
                idBanco = rs.getInt(1);
                nombre = rs.getString(2);
                banco = new Banco(idBanco, nombre);
                bancos.add(banco);
            }
        }
        PoolConnection.getPoolConnection().releaseConnection(conn);
        return bancos;
    }

}
