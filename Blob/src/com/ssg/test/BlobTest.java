package com.ssg.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.ssg.connection.ConnectionData;

public class BlobTest {

	Connection conn = null;
	PreparedStatement pstmt = null;

	@Test
	@SuppressWarnings("static-access")
	public void fileInputTest() {
		conn = ConnectionData.getInstance().getConnection();
		String sql = "insert into userinfo values(?,?,?)";
		int id = 1;
		String name = "aaa";
		File f = new File("×ÀÃæ.png");
		InputStream input = null;
		try {
			input = new FileInputStream(f);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.setBinaryStream(3, input, f.length());
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	@SuppressWarnings("static-access")
	public void fileOutputTest() throws IOException {
		conn = ConnectionData.getInstance().getConnection();
		String sql = "select * from userinfo where id = ?";
		ResultSet rs = null;
		int id = 1;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			File outFile = new File("×ÀÃæ1.png");
			while (rs.next()) {
				String name = rs.getString("name");
				System.out.println("name----->"+name);
				InputStream input = rs.getBinaryStream("photo");
				OutputStream out = null;
				out = new FileOutputStream(outFile);
				int temp = 0;
				while ((temp = input.read()) != -1) {
					out.write(temp);
				}
				input.close();
				out.close();
			}

			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@SuppressWarnings("static-access")
	public void fileBlobOutputTest() throws IOException {
		conn = ConnectionData.getInstance().getConnection();
		String sql = "select * from userinfo where id = ?";
		ResultSet rs = null;
		int id = 1;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			File outFile = new File("×ÀÃæ2.png");
			while (rs.next()) {
				String name = rs.getString("name");
				System.out.println("name----->"+name);
				Blob b = rs.getBlob("photo");
				OutputStream out = null;
				out = new FileOutputStream(outFile);
				out.write(b.getBytes(1, (int) b.length()));
				out.close();
			}

			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
