package com.ssg.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import com.ssg.connection.ConnectionData;

public class StstementTest {

	Connection conn = null;
	Statement stmt = null;

	@Test
	@SuppressWarnings("static-access")
	public void insertTest() {
		conn=ConnectionData.getInstance().getConnection();
		String sql = "insert into userinfo values(1,'ssg',20)";
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void updateTest(){
		conn=ConnectionData.getInstance().getConnection();
		String name = "ssgssg";
		int age = 23;
		String sql = "update userinfo set name='"+name+"',age = "+age+" where id = 1";
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteTest(){
		conn=ConnectionData.getInstance().getConnection();
		String sql = "delete from userinfo where id = 1";
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
