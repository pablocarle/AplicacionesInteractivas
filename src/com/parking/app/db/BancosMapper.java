package com.parking.app.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.parking.app.model.Banco;

public class BancosMapper implements Mapper {
    
    private static BancosMapper instancia = null;
    
    private BancosMapper() {
        super();
    }
    
    public static BancosMapper obtenerMapper() {
        if (instancia == null) {
            instancia = new BancosMapper();
        }
        return instancia;
    }

    @Override
    public int insert(Object o) throws Exception {
        if (o instanceof Banco) {
            Banco banco = (Banco) o;
            Connection conn = PoolConnection.getPoolConnection().getConnection();

            PreparedStatement ps = conn.prepareStatement("insert into bancos (nombre, ftp_out, ftp_in) values (?, ?, ?)");
            ps.setString(1, banco.getNombre());
            ps.setString(2, banco.getFtpOut());
            ps.setString(3, banco.getFtpIn());
            ps.execute();

            PoolConnection.getPoolConnection().releaseConnection(conn);
            return getBancoId(banco.getNombre());
        } else {
            throw new Exception();
        }
    }

    public int getBancoId(String nombre) throws SQLException {
        Connection conn = PoolConnection.getPoolConnection().getConnection();
        PreparedStatement ps = conn.prepareStatement("select idBanco from bancos where nombre=?");
        ps.setString(1, nombre);
        ResultSet rs = ps.executeQuery();
        int id = rs.getInt(1);
        PoolConnection.getPoolConnection().releaseConnection(conn);
        return id;
    }

    @Override
    public void update(Object o) throws Exception {
        // TODO update
        
    }

    @Override
    public void delete(Object d) throws Exception {
    	int idBanco = 0;
    	if (d instanceof Number) {
    		idBanco = ((Number) d).intValue();
    	} else if (d instanceof Banco) {
    		idBanco = ((Banco) d).getIdBanco();
    	} else {
    		throw new Exception("No se reconoce " + d);
    	}
    	Connection conn = PoolConnection.getPoolConnection().getConnection();
    	PreparedStatement ps = conn.prepareStatement("delete from bancos where idBanco = ?");
    	ps.setInt(1, idBanco);
    	ps.execute();
    	PoolConnection.getPoolConnection().releaseConnection(conn);
    }

    @Override
    public Banco select(Object o) throws Exception {
        if (o instanceof Number) {
            Connection conn = PoolConnection.getPoolConnection().getConnection();
            PreparedStatement ps = conn.prepareStatement("select idBanco, nombre, ftp_out, ftp_in from bancos where idBanco = ?");
            ps.setInt(1, ((Number) o).intValue());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Banco banco = new Banco(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                PoolConnection.getPoolConnection().releaseConnection(conn);
                return banco;
            } else {
            	PoolConnection.getPoolConnection().releaseConnection(conn);
                return null;
            }
        } else {
            throw new Exception();
        }
    }

    @Override
    public Collection<Banco> selectAll() throws Exception {
        Connection conn = PoolConnection.getPoolConnection().getConnection();
        PreparedStatement ps = conn.prepareStatement("select idBanco, nombre, ftp_out, ftp_in from bancos");
        Collection<Banco> bancos = new ArrayList<Banco>();

        if (ps.execute()) {
            ResultSet rs = ps.getResultSet();
            int idBanco;
            String nombre;
            String ftpOut;
            String ftpIn;

            Banco banco = null;
            while (rs.next()) {
                idBanco = rs.getInt(1);
                nombre = rs.getString(2);
                ftpOut = rs.getString(3);
                ftpIn = rs.getString(4);
                banco = new Banco(idBanco, nombre, ftpIn, ftpOut);
                bancos.add(banco);
            }
        }
        PoolConnection.getPoolConnection().releaseConnection(conn);
        return bancos;
    }
}
