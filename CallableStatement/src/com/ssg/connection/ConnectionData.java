package com.ssg.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionData {
	public static final String DRIVER="oracle.jdbc.driver.OracleDriver";
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	public static final String USERNAME = "scott";
	public static final String PASSWORD = "oracle";
	static Connection conn = null;
	
	public ConnectionData() {
		// TODO Auto-generated constructor stub
	}
	
	public static ConnectionData instance = new ConnectionData();
	
	public static ConnectionData getInstance(){
		return instance;
	}
	
	public static Connection getConnection(){
		if(conn == null){
			try {
				Class.forName(DRIVER);
				try {
					conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return conn;
	} 
}
