package com.just.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.just.dao.IManager;
import com.just.model.Manager;
import com.just.util.ConnectionManager;

public class ManagerDaoImpl implements IManager{
	private static Connection conn; // 数据库连接
	private static PreparedStatement psmt;// 执行数据库查询
	private static ResultSet rs;// 查询结果（数据库结果集的数据表）
	//添加管理员
	@Override
	public int addManager(Manager manager) {
		int i=0;
		try {
			conn=ConnectionManager.getConnection();
			String sql="insert into Manager values(?,?,?,?,?,?)";
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, manager.getManagerId());
			psmt.setString(2, manager.getManagerName());
			psmt.setString(3, manager.getManagerPwd());
			psmt.setString(4, manager.getManagerGender());
			psmt.setString(5, manager.getManagerPhone());
			psmt.setString(6, manager.getManagerPic());
			i=psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionManager.closeConnection(conn);
			ConnectionManager.closeResultSet(rs);
			ConnectionManager.closeStatement(psmt);
		}
		return i;
	}
	//管理员登录
	@Override
	public Manager getManagerByIdPwd(String managerId, String managerPwd) {
		Manager manager=null;
		try {
			conn=ConnectionManager.getConnection();
			String sql="SELECT * FROM manager WHERE manager_id=? AND manager_password=?";
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, managerId);
			psmt.setString(2, managerPwd);
			rs=psmt.executeQuery();
			while (rs.next()) {
				manager=new Manager(rs.getString("manager_id"),
						rs.getString("manager_name"),
						rs.getString("manager_password"), 
						rs.getString("manager_gender"), 
						rs.getString("manager_phone"),
						rs.getString("manager_pic"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			ConnectionManager.closeConnection(conn);
			ConnectionManager.closeStatement(psmt);
			ConnectionManager.closeResultSet(rs);
		}
		return manager;
	}
	//删除管理员
	@Override
	public int deleteManager(String managerId) {
		int i=0;
		String sql="delete from manager where manager_id=?";
		try {
			conn=ConnectionManager.getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, managerId);
			i=psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			ConnectionManager.closeConnection(conn);
			ConnectionManager.closeResultSet(rs);
			ConnectionManager.closeStatement(psmt);
		}
		return i;
	}
	
	//获取所有管理员
	public List<Manager> getAllManagers() {
		List<Manager> list=new ArrayList<Manager>();
		try {
			conn=ConnectionManager.getConnection();
			String sql="SELECT * FROM manager";
			psmt=conn.prepareStatement(sql);
			rs=psmt.executeQuery();
			while (rs.next()) {
				Manager manager=new Manager(rs.getString("manager_id"),
						rs.getString("manager_name"), 
						rs.getString("manager_password"),
						rs.getString("manager_gender"),
						rs.getString("manager_phone"),
						rs.getString("manager.pic"));
				list.add(manager);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionManager.closeConnection(conn);
			ConnectionManager.closeResultSet(rs);
			ConnectionManager.closeStatement(psmt);
		}
		return list;
	}
	//根据Id查询管理员
	public Manager getManagerById(String managerId) {
		Manager manager=null;
		try {
			conn=ConnectionManager.getConnection();
			String sql="SELECT * FROM manager WHERE manager_id=?";
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, managerId);
			rs=psmt.executeQuery();
			while (rs.next()) {
				manager=new Manager( rs.getString("manager_id"),
						rs.getString("manager_name"),
						rs.getString("manager_password"),
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
	//分页逻辑
	@Override
	public List<Manager> getManagerBypage(int currentpageno,int pageSize) {
		List<Manager> list=new ArrayList<Manager>();
		int beginRecord= (currentpageno - 1) * pageSize;
		int endRecord=pageSize;
		String sql = "select * from manager limit " + beginRecord + ","+ endRecord;		
		try {
			conn=ConnectionManager.getConnection();
			psmt=conn.prepareStatement(sql);
			rs=psmt.executeQuery();
			while (rs.next()) {
				Manager manager=new Manager(rs.getString("manager_id"),
						rs.getString("manager_name"), 
						rs.getString("manager_password"),
						rs.getString("manager_gender"), 
						rs.getString("manager_phone"),
						rs.getString("manager_pic"));
				list.add(manager);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionManager.closeConnection(conn);
			ConnectionManager.closeResultSet(rs);
			ConnectionManager.closeStatement(psmt);
		}
		return list;
	}
	//获取总页数
	public static  int getPageCount(int pageSize) {
		int total=0;//总记录数
		int pageCount=0;//总页数
		String sql="select count(*) from Manager";
		try {
			conn=ConnectionManager.getConnection();
			psmt=conn.prepareStatement(sql);
			rs=psmt.executeQuery();
			while (rs.next()) {
				total = rs.getInt(1);
				pageCount = (total-1)/pageSize+1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionManager.closeConnection(conn);
			ConnectionManager.closeResultSet(rs);
			ConnectionManager.closeStatement(psmt);
		}
		return pageCount;
	}
	//更改管理员
	@Override
	public int updateManager(Manager manager) {
		int i=0;
		String sql="update manager set manager_name=?,manager_password=?,manager_gender=?,manager_phone=?,manager_pic=? where manager_id=? ";
		try {
			conn=ConnectionManager.getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, manager.getManagerName());
			psmt.setString(2, manager.getManagerPwd());
			psmt.setString(3, manager.getManagerGender());
			psmt.setString(4, manager.getManagerPhone());
			psmt.setString(5, manager.getManagerPic());
			psmt.setString(6, manager.getManagerId());
			i=psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionManager.closeConnection(conn);
			ConnectionManager.closeResultSet(rs);
			ConnectionManager.closeStatement(psmt);
		}
		return i;
	}
	

}
