package com.wangc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionManager {
	public static String DRIVER="com.mysql.jdbc.Driver";//�������ݿ�������
	public static String URL="jdbc:mysql://localhost:3306/eschool";//���ݿ�URL
	public static String USER="root";//���ݿ��û���
	public static String PASSWORD="1234";//���ݿ�����
	
	public static Connection getConnection() {
		Connection conn=null;
		try {
			Class.forName(DRIVER);
			conn=DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void closeResultSet(ResultSet rs) {
		if (rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rs=null;
		}
	}
	
	public static void closeStatement(PreparedStatement psmt) {
		if (psmt!=null) {
			try {
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			psmt=null;
		}
	}
	
	public static void closeConnection(Connection conn) {
		if (conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conn=null;
		}
	}
	
}
