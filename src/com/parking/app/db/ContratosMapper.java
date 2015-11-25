package com.parking.app.db;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.parking.app.controller.SistemaCocheras;
import com.parking.app.model.Abono;
import com.parking.app.model.Auto;
import com.parking.app.model.Cheque;
import com.parking.app.model.Cliente;
import com.parking.app.model.Contrato;
import com.parking.app.model.ContratoAbonoCheque;
import com.parking.app.model.ContratoAbonoDebitoCBU;
import com.parking.app.model.ContratoAbonoEfectivo;
import com.parking.app.model.ContratoAbonoTarjeta;
import com.parking.app.model.ContratoHora;
import com.parking.app.model.MedioPago;

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
			c.setActivo(true);
			Connection conn = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement ps = conn.prepareStatement("insert into contratos (idContrato, idCliente, idAuto, idMedioPago, idAbono, precio, fechaInicio, activo) values (?, ?, ?, ?, ?, ?, ?, 1)");
			ps.setInt(1, c.getIdContrato());
			ps.setInt(2, c.getCliente().getIdCliente());
			ps.setInt(3, c.getAuto().getIdAuto());
			ps.setInt(4, c.getMedioPago().getIdMedioPago());
			ps.setInt(5, c.getAbono().getIdAbono());
			ps.setBigDecimal(6, c.getPrecio());
			ps.setDate(7, new java.sql.Date(c.getFechaInicio().getTime()));
			ps.execute();
			PoolConnection.getPoolConnection().releaseConnection(conn);
			int idContrato = getContratoId(c);
			if (c instanceof ContratoAbonoCheque) {
				ContratoAbonoCheque cab = (ContratoAbonoCheque) c;
				for (Cheque cheque : cab.getCheques()) {
					cheque.setIdContrato(idContrato);
					ChequesMapper.getMapper().insert(cheque);
				}
			}
			return idContrato;
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
				int idContrato = rs.getInt(1);
				int idCliente = rs.getInt(2);
				int idAuto = rs.getInt(3);
				int idMedioPago = rs.getInt(4);
				int idAbono = rs.getInt(5);
				BigDecimal precio = rs.getBigDecimal(6);
				Date fechaInicio = rs.getDate(7);
				boolean activo = rs.getBoolean(8);
				PoolConnection.getPoolConnection().releaseConnection(conn);
				return armarContrato(idContrato, idCliente, idAuto, idMedioPago, idAbono, precio, fechaInicio, activo);
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
		int idContrato;
		int idCliente;
		int idAuto;
		int idMedioPago;
		int idAbono;
		BigDecimal precio;
		Date fechaInicio;
		boolean activo;
		while (rs.next()) {
			idContrato = rs.getInt(1);
			idCliente = rs.getInt(2);
			idAuto = rs.getInt(3);
			idMedioPago = rs.getInt(4);
			idAbono = rs.getInt(5);
			precio = rs.getBigDecimal(6);
			fechaInicio = rs.getDate(7);
			activo = rs.getBoolean(8);
			contratos.add(armarContrato(idContrato, idCliente, idAuto, idMedioPago, idAbono, precio, fechaInicio, activo));
		}
		PoolConnection.getPoolConnection().releaseConnection(conn);
		return contratos;
	}
	
	private Contrato armarContrato(int idContrato, int idCliente, int idAuto, int idMedioPago, int idAbono, BigDecimal precio, Date fechaInicio, boolean activo) throws Exception {
		Contrato contrato = null;
		Cliente cliente = ClienteMapper.obtenerMapper().select(idCliente);
		Auto auto = AutosMapper.obtenerMapper().select(idAuto);
		MedioPago medioPago = MediosDePagoMapper.obtenerMapper().select(idMedioPago);
		Abono abono = AbonosMapper.obtenerMapper().select(idAbono);
		if (SistemaCocheras.ABONO_SINABONO == abono.getIdAbono()) {
			contrato = new ContratoHora(-1);
		} else {
			if (SistemaCocheras.MEDIOPAGO_EFECTIVO == medioPago.getIdMedioPago()) {
				contrato = new ContratoAbonoEfectivo();
			} else if (SistemaCocheras.MEDIOPAGO_CHEQUE == medioPago.getIdMedioPago()) {
				List<Cheque> cheques = ChequesMapper.getMapper().selectDeContrato(idContrato);
				contrato = new ContratoAbonoCheque(cheques);
			} else if (medioPago.isTarjeta()) {
				contrato = new ContratoAbonoTarjeta();
			} else {
				contrato = new ContratoAbonoDebitoCBU();
			}
		}
		if (contrato != null) {
			contrato.setIdContrato(idContrato);
			contrato.setAbono(abono);
			contrato.setActivo(activo);
			contrato.setAuto(auto);
			contrato.setCliente(cliente);
			contrato.setFechaInicio(fechaInicio);
			contrato.setMedioPago(medioPago);
			contrato.setPrecio(precio);
		}
		return contrato;
	}
}
