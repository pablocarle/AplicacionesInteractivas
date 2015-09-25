package com.parking.app.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.parking.app.model.Contrato;

public class ContratosMapper implements Mapper {

	private static ContratosMapper instancia = null;
	
	private ContratosMapper() {
		super();
	}
	
	public static ContratosMapper obtenerMapper() {
		if (instancia == null) {
			instancia = new ContratosMapper();
		}
		return instancia;
	}
	
	public int insert(Object o) throws Exception {
		if (o instanceof Contrato) {
			Contrato c = (Contrato) o;
			Connection conn = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement ps = conn.prepareStatement("insert into contratos (idContrato, idCliente, idAuto, idMedioPago, idAbono, precio, fechaInicio, activo) values (?, ?, ?, ?, ?, ?, ?, true)");
			ps.setInt(1, c.getIdContrato());
			ps.setInt(2, c.getCliente().getIdCliente());
			ps.setString(3, c.getAuto().getPatente());
			ps.setInt(4, c.getMedioPago().getIdMedioPago());
			ps.setInt(5, c.getAbono().getIdAbono());
			ps.setBigDecimal(6, c.getPrecio());
			ps.setDate(7, new java.sql.Date(c.getFechaInicio().getTime()));
			ps.execute();
			PoolConnection.getPoolConnection().releaseConnection(conn);
			return getContratoId(c);
		} else {
			throw new ClassCastException();
		}
	}

	private int getContratoId(Contrato c) throws Exception {
		if (c.getIdContrato() <= 0) {
			Connection conn = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement ps = conn.prepareStatement("select idContrato from contratos where idCliente = ? and idAuto = ? and activo = ?");
			ps.setInt(1, c.getCliente().getIdCliente());
			ps.setString(2, c.getAuto().getPatente());
			ps.setBoolean(3, c.isActivo());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				try {
					return rs.getInt(1);
				} finally {
					PoolConnection.getPoolConnection().releaseConnection(conn);
				}
			} else {
				throw new Exception("No se encontro el contrato solicitado");
			}
		} else {
			return c.getIdContrato();
		}
	}

	public void update(Object o) throws Exception {
		if (o instanceof Contrato) {
			Contrato c = (Contrato) o;
			Connection conn = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement ps = conn.prepareStatement("update contratos set"
					+ " idCliente = ?,"
					+ " idAuto = ?,"
					+ " idMedioPago = ?,"
					+ " idAbono = ?,"
					+ " precio = ?,"
					+ " fechaInicio = ?,"
					+ " activo = ? where idContrato = ?");
			ps.setInt(1, c.getCliente().getIdCliente());
			ps.setString(2, c.getAuto().getPatente());
			ps.setInt(3, c.getMedioPago().getIdMedioPago());
			ps.setInt(4, c.getAbono().getIdAbono());
			ps.setBigDecimal(5, c.getPrecio());
			ps.setDate(6, new java.sql.Date(c.getFechaInicio().getTime()));
			ps.setBoolean(7, c.isActivo());
			ps.setInt(8, c.getIdContrato());
			ps.executeUpdate();
			PoolConnection.getPoolConnection().releaseConnection(conn);
		} else {
			throw new Exception();
		}
	}

	public void delete(Object o) throws Exception {
		if (o instanceof Contrato) {
			Contrato c = (Contrato) o;
			c.setActivo(false);
			update(o);
		} else {
			throw new Exception();
		}
	}

	public Contrato select(Object o) throws Exception {
		if (o instanceof Number) {
			Connection conn = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement ps = conn.prepareStatement("select idContrato, idCliente, idAuto, idMedioPago, idAbono, precio, fechaInicio, activo from contratos where idContrato = ?");
			ps.setInt(1, ((Number) o).intValue());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Contrato contrato = new Contrato();
				contrato.setIdContrato(rs.getInt(1));
				contrato.setCliente(ClienteMapper.obtenerMapper().select(rs.getInt(2)));
				contrato.setAuto(AutosMapper.obtenerMapper().select(rs.getInt(3)));
				contrato.setMedioPago(MediosDePagoMapper.obtenerMapper().select(rs.getInt(4)));
				contrato.setAbono(AbonosMapper.obtenerMapper().select(rs.getInt(5)));
				contrato.setPrecio(rs.getBigDecimal(6));
				contrato.setFechaInicio(rs.getDate(7));
				contrato.setActivo(rs.getBoolean(8));
				return contrato;
			} else {
				return null;
			}
		} else {
			throw new Exception();
		}
	}

	public Collection<Contrato> selectAll() throws Exception {
		List<Contrato> contratos = new ArrayList<Contrato>();
		Connection conn = PoolConnection.getPoolConnection().getConnection();
		PreparedStatement ps = conn.prepareStatement("select idContrato, idCliente, idAuto, idMedioPago, idAbono, precio, fechaInicio, activo from contratos");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Contrato contrato = new Contrato();
			contrato.setIdContrato(rs.getInt(1));
			contrato.setCliente(ClienteMapper.obtenerMapper().select(rs.getInt(2)));
			contrato.setAuto(AutosMapper.obtenerMapper().select(rs.getInt(3)));
			contrato.setMedioPago(MediosDePagoMapper.obtenerMapper().select(rs.getInt(4)));
			contrato.setAbono(AbonosMapper.obtenerMapper().select(rs.getInt(5)));
			contrato.setPrecio(rs.getBigDecimal(6));
			contrato.setFechaInicio(rs.getDate(7));
			contrato.setActivo(rs.getBoolean(8));
			contratos.add(contrato);
		}
		return contratos;
	}
}
