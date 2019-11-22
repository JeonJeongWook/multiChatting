package com.example.db;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connection {
	String jdbc = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost/multichat?serverTimezone=UTC";
	String user = "root";
	String password = "1234";
	
	ResultSet rs = null;
	Statement stmt = null;
	java.sql.Connection conn = null;
	PreparedStatement pstm = null;
	
	public Connection() {
		try {
			Class.forName(jdbc);
			System.out.println(" ** db 접속 ** ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int doRegister(String id, String pw) {
		System.out.println("회원가입 id > " + id + " / pw > " + pw);
		int check = 0;
		try {
			conn = DriverManager.getConnection(url, user, password);
			
			String sql = "insert into user values(?, ?, ?)";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1,  0);
			pstm.setString(2, id);
			pstm.setString(3, pw);
			check = pstm.executeUpdate();	//쿼리실행
			System.out.println(check);
		} catch (SQLException e) {
			e.getStackTrace();
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return check;
	}
}
