package com.parking.app.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.parking.app.controller.SistemaCocheras;
import com.parking.app.model.Banco;
import com.parking.app.model.Cliente;
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

            PreparedStatement ps = conn.prepareStatement("insert into mediospago (nombre, descripcion, ftp_out, ftp_in, archivo, idBanco) values (?, ?, ?, ?, ?, ?)");
            ps.setString(1, medioPago.getNombre());
            ps.setString(2, medioPago.getDescripcion());
            ps.setString(3, medioPago.getFtpOut());
            ps.setString(4, medioPago.getFtpIn());
            ps.setString(5, medioPago.getArchivo());
            ps.setString(6, Integer.toString(medioPago.getBanco().getIdBanco()));
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
    public Collection<MedioPago> selectAll() throws Exception {
        Connection conn = PoolConnection.getPoolConnection().getConnection();
        PreparedStatement ps = conn.prepareStatement("select idMedioPago, mediospago.nombre, descripcion, ftp_out, ftp_in, archivo, bancos.idBanco, bancos.nombre from mediospago JOIN bancos where mediospago.idBanco = bancos.idBanco");
        Collection<MedioPago> mediosPago = new ArrayList<MedioPago>();

        if (ps.execute()) {
            ResultSet rs = ps.getResultSet();
            int idMedioPago;
            String nombre;
            String descripcion;
            String ftpOut;
            String ftpIn;
            String archivo;
            int idBanco; 
            String nombreBanco;

            MedioPago medioPago = null;
            while (rs.next()) {
                idMedioPago = rs.getInt(1);
                nombre = rs.getString(2);
                descripcion = rs.getString(3);
                ftpOut = rs.getString(4);
                ftpIn = rs.getString(5);
                archivo = rs.getString(6);
                idBanco = rs.getInt(7);
                nombreBanco = rs.getString(8);
                Banco banco = new Banco(idBanco, nombreBanco);
                medioPago = new MedioPago(nombre, banco, descripcion, ftpOut, ftpIn, archivo);
                medioPago.setIdMedioPago(idMedioPago);
                mediosPago.add(medioPago);
            }
        }
        PoolConnection.getPoolConnection().releaseConnection(conn);
        return mediosPago;
    }

}
