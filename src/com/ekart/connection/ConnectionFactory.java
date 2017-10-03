package com.ekart.connection;

import java.sql.Connection;
import com.microsoft.applicationinsights.*;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	String driverClassName = "com.mysql.jdbc.Driver";
	String connectionUrl = "jdbc:mysql://cadmysql.mysql.database.azure.com:3306/eCommerce?verifyServerCertificate=true&useSSL=true&requireSSL=false";
			//+ "myDbConn = DriverManager.getConnection(url, \"cadadmin@cadmysql\",microsoft@1);";
	String dbUser = "cadadmin@cadmysql";
	String dbPwd = "microsoft@1";

	private static ConnectionFactory connectionFactory = null;

	private ConnectionFactory() {
		try {
			Class.forName(driverClassName);
		} catch (ClassNotFoundException ex) {
			TelemetryClient tc=new TelemetryClient();
			tc.trackException(ex);
		}
	}

	@SuppressWarnings("finally")
	public Connection getConnection() {
		Connection conn=null;
		try
		{
		
		conn = DriverManager.getConnection(connectionUrl, dbUser, dbPwd);
		return conn;
		}
		catch(SQLException ex)
		{
			TelemetryClient tc=new TelemetryClient();
			tc.trackException(ex);
		}
		finally
		{
			return conn;
		}
		
		
	}

	public static ConnectionFactory getInstance() {
		if (connectionFactory == null) {
			connectionFactory = new ConnectionFactory();
		}
		return connectionFactory;
	}

}
