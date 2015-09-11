package com.parking.app.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

public class SetupTables {
    public static void main(String[] args) {
        Connection con = null;
        try{
        con = PoolConnection.getPoolConnection().getConnection();
        setupClientes(con);
        } catch(Exception e) {
            System.out.println("Mensaje Error: " + e.getMessage());
            System.out.println("Stack Trace: " + e.getStackTrace());
        } finally {
            PoolConnection.getPoolConnection().realeaseConnection(con);
        }
    }
    private static void setupClientes(Connection con) throws SQLException, IOException {
        Statement statement = con.createStatement();
        statement.setQueryTimeout(30);  // set timeout to 30 sec.

        File file = new File("setup.sql");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuffer dbSetup = new StringBuffer();
        String line = null;

        while((line = reader.readLine()) != null) {
            dbSetup.append(line);
        }
        reader.close();
        statement.executeUpdate(dbSetup.toString());
        ResultSet result = statement.executeQuery("select * from clientes");
        while (result.next())
        {
            int id = result.getInt(1);
            String nombre = result.getString(2);
            System.out.println(id + " " + nombre);
        }
    }
}
