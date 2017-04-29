package com.wangc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.wangc.dao.IManager;
import com.wangc.model.Manager;
import com.wangc.util.ConnectionManager;

public class ManagerDaoImpl implements IManager {
	private static Connection conn;//数据库连接
	private static PreparedStatement psmt;//执行数据库操作
	private static ResultSet rs;//数据库执行结果

	@Override
	public Manager getManagerByIdPwd(String managerId, String managerPwd) {
		Manager manager = null;
		try {
			conn=ConnectionManager.getConnection();
			String sql="SELECT * FROM manager WHERE manager_id=? AND manager_password=?";
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, managerId);
			psmt.setString(2, managerPwd);
			rs=psmt.executeQuery();
			while (rs.next()) {
				manager=new Manager(managerId, 
						rs.getString("manager_name"),
						managerPwd, 
						rs.getString("manager_gender"), 
						rs.getString("manager_phone"), 
						rs.getString("manager_pic"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionManager.closeConnection(conn);
			ConnectionManager.closeResultSet(rs);
			ConnectionManager.closeStatement(psmt);
		}
		return manager;
	}

}
