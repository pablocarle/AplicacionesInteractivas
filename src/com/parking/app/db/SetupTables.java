package com.parking.app.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SetupTables {
    public static void main(String[] args) {
        Connection con = null;
        try{
        con = PoolConnection.getPoolConnection().getConnection();
        setupClientes(con);
        } catch(SQLException e) {
            System.out.println("Mensaje Error: " + e.getMessage());
            System.out.println("Stack Trace: " + e.getStackTrace());
        } finally {
            PoolConnection.getPoolConnection().realeaseConnection(con);
        }
    }
    private static void setupClientes(Connection con) throws SQLException {
        Statement statement = con.createStatement();
        statement.setQueryTimeout(30);  // set timeout to 30 sec.

        statement.executeUpdate("drop table if exists clientes");
        statement.executeUpdate("CREATE TABLE clientes (idCliente integer, nombre varchar(255), domicilio varchar(255), telefono varchar(255), email varchar(255), activo integer, autos integer, primary key (idCliente));");
        statement.executeUpdate("insert into clientes values(1, 'Juan Perez', 'Cochabamba 332', '467532463', 'email@localhost', 1, null)");
        statement.executeUpdate("insert into clientes values(2, 'Jorge Roque', 'San Juan 552', '7654335', 'email@demo', 1, null)");
        statement.executeUpdate("insert into clientes values(3, 'Pedro Perez', 'Av. Independencia 552', '157654335', 'email2@demo', 0, null)");
        ResultSet result = statement.executeQuery("select * from clientes");
        while (result.next())
        {
            int id = result.getInt(1);
            String nombre = result.getString(2);
            System.out.println(id + " " + nombre);
        }
    }
}
