package com.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {
	private final String jdbc = "com.mysql.jdbc.Driver";
	private final String url = "jdbc:mysql://localhost/multichat?serverTimezone=UTC"; 
	private final String id = "root";
	private final String pw = "1234";
	
	public DBManager() {
		Connection conn = null;
		Statement state = null;
		
		try {
			Class.forName(jdbc);
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("** MySql Connection **");
			state = conn.createStatement();
			
			String sql;
			sql = "select * from user";
			ResultSet rs = state.executeQuery(sql);
			
			while(rs.next()) {
				String id = rs.getString("id");
				String pw = rs.getString("pw");
				System.out.println("id " + id);
				System.out.println("pw " + pw);
			}
			rs.close();
			state.close();
			conn.close();
		} catch (Exception e) {
		} finally {
			try {
				if(state != null)
					state.close();
			}catch(SQLException ex1) {
				
			}
			try {
				if(conn!=null)
					conn.close();
			}catch (SQLException ex1) {
			}
		}
		System.out.println(" ** MySql Close ** ");
	}
}
