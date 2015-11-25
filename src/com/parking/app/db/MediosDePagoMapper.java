package com.parking.app.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.parking.app.model.Banco;
import com.parking.app.model.MedioPago;

public class MediosDePagoMapper implements Mapper {
    private static MediosDePagoMapper instancia = null;

    private MediosDePagoMapper() {
        super();
    }

    public static MediosDePagoMapper obtenerMapper() {
        if (instancia == null) {
            instancia = new MediosDePagoMapper();
        }
        return instancia;
    }

    @Override
    public int insert(Object o) throws Exception {
        if (o instanceof MedioPago) {
            MedioPago medioPago = (MedioPago) o;
            Connection conn = PoolConnection.getPoolConnection().getConnection();
            PreparedStatement ps;

            if (medioPago.getBanco() == null) {
                ps = conn.prepareStatement("insert into mediospago (nombre, descripcion) values (?, ?)");
            } else {
                ps = conn.prepareStatement("insert into mediospago (nombre, descripcion, idBanco) values (?, ?, ?)");
                ps.setString(3, Integer.toString(medioPago.getBanco().getIdBanco()));
            }

            ps.setString(1, medioPago.getNombre());
            ps.setString(2, medioPago.getDescripcion());

            ps.execute();

            PoolConnection.getPoolConnection().releaseConnection(conn);
            return getMedioPagoId(medioPago.getNombre());
        } else {
            throw new Exception();
        }
    }

    public int getMedioPagoId(String nombre) throws SQLException {
        Connection conn = PoolConnection.getPoolConnection().getConnection();
        PreparedStatement ps = conn.prepareStatement("select idMedioPago from mediospago where nombre=?");
        ps.setString(1, nombre);
        ResultSet rs = ps.executeQuery();
        int idMedioPago = rs.getInt(1);
        PoolConnection.getPoolConnection().releaseConnection(conn);
        return idMedioPago;
    }

    @Override
    public void update(Object o) throws Exception {
        // TODO update

    }

    @Override
    public void delete(Object idMedioPago) throws Exception {
        if (idMedioPago instanceof Integer) {
            Connection conn = PoolConnection.getPoolConnection().getConnection();
            PreparedStatement ps = conn.prepareStatement("delete from mediospago where idMedioPago = ?");
            ps.setInt(1, (Integer) idMedioPago);
            ps.executeUpdate();
            PoolConnection.getPoolConnection().releaseConnection(conn);
        } else {
            throw new Exception();
        }
    }

    @Override
    public MedioPago select(Object o) throws Exception {
    	int id = 0;
    	MedioPago medioPago = null;
    	if (o instanceof Number) {
    		id = ((Number) o).intValue();
    	}
    	Connection conn = PoolConnection.getPoolConnection().getConnection();
    	PreparedStatement ps = conn.prepareStatement("select idMedioPago, nombre, descripcion, idBanco from mediospago where idMedioPago = ?");
    	ps.setInt(1, id);
    	if (ps.execute()) {
    		ResultSet rs = ps.getResultSet();
    		if (rs.next()) {
    			int idMedioPago = rs.getInt(1);
                String nombre = rs.getString(2);
                String descripcion = rs.getString(3);
                Integer idBanco = rs.getInt(4); 
                Banco banco = BancosMapper.obtenerMapper().select(idBanco);
                medioPago = new MedioPago(nombre, banco, descripcion);
                medioPago.setIdMedioPago(idMedioPago);
    		}
    	}
    	
    	PoolConnection.getPoolConnection().releaseConnection(conn);
        return medioPago;
    }

    @Override
    public Collection<MedioPago> selectAll() throws Exception {
        Connection conn = PoolConnection.getPoolConnection().getConnection();
        Collection<Banco> bancos = BancosMapper.obtenerMapper().selectAll(); 
        PreparedStatement ps = conn.prepareStatement("select idMedioPago, nombre, descripcion, idBanco from mediospago");
        Collection<MedioPago> mediosPago = new ArrayList<MedioPago>();

        if (ps.execute()) {
            ResultSet rs = ps.getResultSet();
            int idMedioPago;
            String nombre;
            String descripcion;
            Banco banco = null;
            Integer idBanco; 

            MedioPago medioPago = null;
            while (rs.next()) {
                idMedioPago = rs.getInt(1);
                nombre = rs.getString(2);
                descripcion = rs.getString(3);
                idBanco = rs.getInt(4);
                if (idBanco != null) {
                    for (Banco b : bancos) {
                        if (b.getIdBanco() == idBanco) {
                            banco = b;
                        }
                    }
                }
                medioPago = new MedioPago(nombre, banco, descripcion);
                medioPago.setIdMedioPago(idMedioPago);
                mediosPago.add(medioPago);
            }
        }
        PoolConnection.getPoolConnection().releaseConnection(conn);
        return mediosPago;
    }
}
