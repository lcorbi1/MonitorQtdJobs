package com.macys.factory;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
	
	public static Properties getProp() throws IOException {
		Properties props = new Properties();
		//FileInputStream file = new FileInputStream("C:\\Users\\Lucas\\TrackerQtdJobsMacys\\MonitorQtdJobs\\src\\com\\macys\\factory\\configs.properties");
		FileInputStream file = new FileInputStream("configs.properties");
		props.load(file);
		return props;

	}	
			
	public Connection conectar() throws SQLException, ClassNotFoundException, IOException {
		
		Properties prop = getProp();
		
		//private static String userDB = "emuser";
		String userDB = prop.getProperty("prop.server.user");
		//private static String passwordDB = "dqm50vnc";
		String passwordDB = prop.getProperty("prop.server.password");
		//private static String databaseApp = "emdb";
		String databaseApp = prop.getProperty("prop.server.dbname");
		//private static String URL = "jdbc:postgresql://192.168.182.165:5432/" + databaseApp + "?useTimezone=true&serverTimezone=UTC";
		//String databaseApp = "em900";
		String URL = "jdbc:postgresql://"+ prop.getProperty("prop.server.host") + ":5432/" + databaseApp + "?useTimezone=true&serverTimezone=UTC";
	
		//Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/ppdocesweb?useTimezone=true&serverTimezone=UTC","root","dqm50vnc");
		Class.forName("org.postgresql.Driver");  // load and register JDBC driver for PostgresSQL
		Connection connection = DriverManager.getConnection(URL,userDB,passwordDB);
		return connection;
		
	
	}
		
}


