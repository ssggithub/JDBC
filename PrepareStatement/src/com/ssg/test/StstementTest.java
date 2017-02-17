package com.ssg.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;

import com.ssg.connection.ConnectionData;

public class StstementTest {

	Connection conn = null;
	PreparedStatement pstmt = null;

	@Test
	@SuppressWarnings("static-access")
	public void insertTest() {
		conn=ConnectionData.getInstance().getConnection();
		String sql = "insert into userinfo values(?,?,?)";
		int id = 3;
		String name = "aaa";
		int age = 3;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.setInt(3, age);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void updateTest(){
		conn=ConnectionData.getInstance().getConnection();
		int id = 3;
		String name = "bbb";
		int age = 4;
		String sql = "update userinfo set name=?,age = ? where id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setInt(2, age);
			pstmt.setInt(3, id);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteTest(){
		conn=ConnectionData.getInstance().getConnection();
		String sql = "delete from userinfo where id = ?";
		int id = 3;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
