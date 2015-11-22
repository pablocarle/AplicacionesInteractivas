package com.parking.app.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.parking.app.controller.SistemaCocheras;
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
    public void delete(Object d) throws Exception {
        // TODO delete

    }

    @Override
    public MedioPago select(Object o) throws Exception {
        // TODO select
        return null;
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
