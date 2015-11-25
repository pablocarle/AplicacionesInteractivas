package com.parking.app.db;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.parking.app.model.Cheque;

public class ChequesMapper implements Mapper {

	private static ChequesMapper instance = null;
	
	private ChequesMapper() {
		super();
	}
	
	public static ChequesMapper getMapper() {
		if (instance == null) {
			instance = new ChequesMapper();
		}
		return instance;
	}
	
	@Override
	public int insert(Object o) throws Exception {
		if (o instanceof Cheque) {
			Cheque c = (Cheque) o;
			Connection conn = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement ps = conn.prepareStatement("insert into cheques (idCheque, idContrato, monto, numero, entidad, fecha) values (?,?,?,?,?,?)");
			ps.setInt(1, c.getIdCheque());
			ps.setInt(2, c.getIdContrato());
			ps.setBigDecimal(3, c.getMonto());
			ps.setString(4, c.getNumero());
			ps.setString(5, c.getEntidad());
			ps.setDate(6, new java.sql.Date(c.getFecha().getTime()));
			ps.execute();
			PoolConnection.getPoolConnection().releaseConnection(conn);
			return 0;
		} else {
			throw new Exception();
		}
	}

	@Override
	public void update(Object o) throws Exception {
		// TODO update
		throw new Exception("No implementado");
	}

	@Override
	public void delete(Object d) throws Exception {
//		TODO delete
		throw new Exception("No implementado");
	}

	@Override
	public Object select(Object o) throws Exception {
		int id = 0;
		Cheque cheque = null;
		if (o instanceof Number) {
			id = ((Number) o).intValue();
		}
		Connection conn = PoolConnection.getPoolConnection().getConnection();
		PreparedStatement ps = conn.prepareStatement("select idCheque, idContrato, fecha, monto, entidad, numero from cheques where idCheque = ?");
		ps.setInt(1, id);
		if (ps.execute()) {
			ResultSet rs = ps.getResultSet();
			Date fecha;
			BigDecimal monto;
			String entidad;
			String numero;
			int idCheque;
			Integer idContrato = null;
			while (rs.next()) {
				idCheque = rs.getInt(1);
				idContrato = rs.getInt(2);
				fecha = rs.getDate(3);
				monto = rs.getBigDecimal(4);
				entidad = rs.getString(5);
				numero = rs.getString(6);
				cheque = new Cheque();
				cheque.setEntidad(entidad);
				cheque.setFecha(fecha);
				cheque.setIdCheque(idCheque);
				cheque.setIdContrato(idContrato);
				cheque.setMonto(monto);
				cheque.setNumero(numero);
			}
		}
		PoolConnection.getPoolConnection().releaseConnection(conn);
        return cheque;
	}

	@Override
	public Collection<Cheque> selectAll() throws Exception {
		Collection<Cheque> cheques = new ArrayList<Cheque>();
		Connection conn = PoolConnection.getPoolConnection().getConnection();
		PreparedStatement ps = conn.prepareStatement("select idCheque, idContrato, fecha, monto, entidad, numero from cheques");
		if (ps.execute()) {
			ResultSet rs = ps.getResultSet();
			int idCheque;
			int idContrato;
			Date fecha;
			BigDecimal monto;
			String entidad;
			String numero;
			Cheque c = null;
			while (rs.next()) {
				idCheque = rs.getInt(1);
				idContrato = rs.getInt(2);
				fecha = rs.getDate(3);
				monto = rs.getBigDecimal(4);
				entidad = rs.getString(5);
				numero = rs.getString(6);
				c = new Cheque();
				c.setIdCheque(idCheque);
				c.setIdContrato(idContrato);
				c.setFecha(fecha);
				c.setMonto(monto);
				c.setEntidad(entidad);
				c.setNumero(numero);
				cheques.add(c);
			}
		}
		PoolConnection.getPoolConnection().releaseConnection(conn);
		return cheques;
	}

	public List<Cheque> selectDeContrato(int idContrato) throws Exception {
		List<Cheque> cheques = new ArrayList<Cheque>();
		Connection conn = PoolConnection.getPoolConnection().getConnection();
		PreparedStatement ps = conn.prepareStatement("select idCheque, idContrato, fecha, monto, entidad, numero from cheques where idContrato = ?");
		ps.setInt(1, idContrato);
		
		if (ps.execute()) {
			ResultSet rs = ps.getResultSet();
			int idCheque;
			Date fecha;
			BigDecimal monto;
			String entidad;
			String numero;
			Cheque c = null;
			while (rs.next()) {
				idCheque = rs.getInt(1);
				idContrato = rs.getInt(2);
				fecha = rs.getDate(3);
				monto = rs.getBigDecimal(4);
				entidad = rs.getString(5);
				numero = rs.getString(6);
				c = new Cheque();
				c.setIdCheque(idCheque);
				c.setIdContrato(idContrato);
				c.setFecha(fecha);
				c.setMonto(monto);
				c.setEntidad(entidad);
				c.setNumero(numero);
				cheques.add(c);
			}
		}
		PoolConnection.getPoolConnection().releaseConnection(conn);
		return cheques;
	}
}
