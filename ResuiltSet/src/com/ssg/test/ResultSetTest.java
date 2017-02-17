package com.ssg.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import com.ssg.connection.ConnectionData;

public class ResultSetTest {

	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	@Test
	public void testStatementResultSet() {
		conn = ConnectionData.getInstance().getConnection();
		String sql = "select * from userinfo";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getNString("name");
				int age = rs.getInt("age");
				System.out.println("id----->" + id);
				System.out.println("name--->" + name);
				System.out.println("age---->" + age);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testPrepareStatementResultSet() {
		conn = ConnectionData.getInstance().getConnection();
		String sql = "select * from userinfo where id = ?";
		try {
			int id = 3;
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int ids = rs.getInt("id");
				String names = rs.getNString("name");
				int ages = rs.getInt("age");
				System.out.println("id----->" + id);
				System.out.println("name--->" + names);
				System.out.println("age---->" + ages);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
