package com.example.db;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

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
			if(e.getSQLState().startsWith("23")) {
				JOptionPane.showMessageDialog(null, "이미 존재하는 아이디 입니다.");
			}
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

	public String doLogin(String id, String pw) {
		String db_id = "", db_pw = "";
		String result = "";	//""-실패 / 1-성공
		try {
			conn = DriverManager.getConnection(url, user, password);
			
			String sql = "select * from user where id = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, id);
			rs = pstm.executeQuery();
			while(rs.next()) {
				db_id = rs.getString("id");
				db_pw = rs.getString("pw");
			}
			if(db_pw.equals(pw)) {
				System.out.println("비밀번호가 일치합니다");
				result = db_id;
			}else {
				System.out.println("비밀번호가 틀렸습니다.");
				result = "";
			}
		}catch (Exception e) {
			e.getStackTrace();
		}
		System.out.println(db_id + "/" + db_pw);
		return result;
	}
}
