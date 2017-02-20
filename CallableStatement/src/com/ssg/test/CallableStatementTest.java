package com.ssg.test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import org.junit.Test;

import com.ssg.connection.ConnectionData;

public class CallableStatementTest {

	Connection conn = null;
	CallableStatement cstmt = null;
	
	@Test
	public void test() throws SQLException{
		conn = ConnectionData.getInstance().getConnection();
		String sql = "{Call myproc(?,?,?)}";
		cstmt = conn.prepareCall(sql);
		cstmt.setInt(1, 2);
		cstmt.setInt(2, 2);
		cstmt.registerOutParameter(3, Types.VARCHAR);
		cstmt.execute();
		System.out.println("name--------->"+cstmt.getString(3));
		cstmt.close();
		conn.close();
	}
}
