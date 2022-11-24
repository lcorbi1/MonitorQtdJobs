package com.macys.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private static String userDB = "emuser";
	private static String passwordDB = "dqm50vnc";
	private static String databaseApp = "emdb";
	private static String URL = "jdbc:postgresql://192.168.182.165:5432/" + databaseApp + "?useTimezone=true&serverTimezone=UTC";
		
	public Connection conectar() throws SQLException, ClassNotFoundException {
	
		//Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/ppdocesweb?useTimezone=true&serverTimezone=UTC","root","dqm50vnc");
		Class.forName("org.postgresql.Driver");  // load and register JDBC driver for PostgresSQL
		Connection connection = DriverManager.getConnection(URL,userDB,passwordDB);
		return connection;
		
	
	}
	
}


