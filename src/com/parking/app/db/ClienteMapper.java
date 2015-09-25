package com.parking.app.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.parking.app.model.Cliente;

public class ClienteMapper implements Mapper {
	
	private static ClienteMapper instancia = null;
	
	private ClienteMapper() {
		super();
	}
	
	public static ClienteMapper obtenerMapper() {
		if (instancia == null) {
			instancia = new ClienteMapper();
		}
		return instancia;
	}

	public int insert(Object o) throws Exception {
		if (o instanceof Cliente) {
			Cliente cliente = (Cliente) o;
			Connection conn = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement ps = conn.prepareStatement("insert into clientes (nombre, domicilio, telefono, email, activo) values (?, ?, ?, ?, 1)");
			ps.setString(1, cliente.getNombre());
			ps.setString(2, cliente.getDomicilio());
			ps.setString(3, cliente.getTelefono());
			ps.setString(4, cliente.getEmail());
			ps.execute();

            PoolConnection.getPoolConnection().releaseConnection(conn);
			return getClientId(cliente.getEmail());
		} else {
			throw new Exception();
		}
	}

	private int getClientId(String email) throws SQLException {
	    Connection conn = PoolConnection.getPoolConnection().getConnection();
	    PreparedStatement ps = conn.prepareStatement("select idCliente from clientes where email=?");
	    ps.setString(1, email);
	    ResultSet rs = ps.executeQuery();
	    int idCliente = rs.getInt(1);
	    PoolConnection.getPoolConnection().releaseConnection(conn);
        return idCliente;
	}

	public void update(Object o) throws Exception {
		if (o instanceof Cliente) {
			Cliente cliente = (Cliente) o;

				Connection conn = PoolConnection.getPoolConnection().getConnection();
				PreparedStatement ps = conn.prepareStatement("update clientes set nombre = ?, domicilio = ?, telefono = ?, email = ? where idCliente = ?");
	            ps.setString(1, cliente.getNombre());
	            ps.setString(2, cliente.getDomicilio());
	            ps.setString(3, cliente.getTelefono());
	            ps.setString(4, cliente.getEmail());
	            ps.setInt(5, cliente.getIdCliente());
	            ps.executeUpdate();
				PoolConnection.getPoolConnection().releaseConnection(conn);
		} else {
			throw new Exception();
		}
	}

	public void delete(Object o) throws Exception {
		if (o instanceof Cliente) {
			Cliente cliente = (Cliente) o;
			Connection conn = PoolConnection.getPoolConnection().getConnection();
			cliente.setActivo(false);
			PreparedStatement ps = conn.prepareStatement("update clientes set activo = 0 where idCliente = ?");
			ps.setInt(1, cliente.getIdCliente());
			ps.executeUpdate();
			PoolConnection.getPoolConnection().releaseConnection(conn);
		} else {
			throw new Exception();
		}
	}

	public Cliente select(Object o) throws Exception {
	    Cliente cliente = null;
		if (o instanceof Cliente) {
			cliente = (Cliente) o;
		} else {
			throw new Exception();
		}
		return cliente;
	}

	public Collection<Cliente> selectAll() throws Exception {
		Connection conn = PoolConnection.getPoolConnection().getConnection();
		PreparedStatement ps = conn.prepareStatement("select idCliente, nombre, domicilio, telefono, email, activo from clientes where activo=1");
		Collection<Cliente> clientes = new ArrayList<Cliente>();
		if (ps.execute()) {
			ResultSet rs = ps.getResultSet();
			int idCliente;
			String nombre;
			String domicilio;
			String telefono;
			String email;
			boolean activo;
			Cliente cliente = null;
			while (rs.next()) {
				idCliente = rs.getInt(1);
				nombre = rs.getString(2);
				domicilio = rs.getString(3);
				telefono = rs.getString(4);
				email = rs.getString(5);
				activo = rs.getBoolean(6);
				cliente = new Cliente(idCliente, nombre, domicilio, telefono, email, activo);
				clientes.add(cliente);
			}
		}
		PoolConnection.getPoolConnection().releaseConnection(conn);
		return clientes;
	}
}
