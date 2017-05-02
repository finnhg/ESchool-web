package com.wangc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.wangc.dao.IUserAdvice;
import com.wangc.model.UserAdvice;
import com.wangc.util.ConnectionManager;

public class UserAdviceDaoImpl implements IUserAdvice{
	private static Connection conn;
	private static PreparedStatement psmt;
	private static ResultSet rs;
	
	//获取userAdvice总页数
	public static int getPageCount(int pageSize) {
		int pageCount=0;
		int totalCount=0;
		String sql="SELECT COUNT(*) FROM user_advice";
		try {
			conn=ConnectionManager.getConnection();
			psmt=conn.prepareStatement(sql);
			rs=psmt.executeQuery();
			while (rs.next()) {
				totalCount=rs.getInt(1);
			}
			pageCount=(totalCount-1)/pageSize+1;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionManager.closeConnection(conn);
			ConnectionManager.closeResultSet(rs);
			ConnectionManager.closeStatement(psmt);
		}
		return pageCount;
	}
	
	//分页获取userAdvice
	@Override
	public List<UserAdvice> getUserAdvicesByPage(int currentPageNo, int pageSize) {
		List<UserAdvice> list=new ArrayList<>();
		int beginRecord=(currentPageNo-1)*pageSize;
		int endRecord=pageSize;
		String sql="select * from user_advice limit "+beginRecord+","+endRecord;
		try {
			conn=ConnectionManager.getConnection();
			psmt=conn.prepareStatement(sql);
			rs=psmt.executeQuery();
			while (rs.next()) {
				UserAdvice userAdvice=new UserAdvice(
						rs.getString("advice_id"), 
						rs.getString("user_id"), 
						rs.getString("user_name"), 
						rs.getString("advice_keyword"), 
						rs.getString("user_advice"), 
						rs.getString("advice_time"));
				list.add(userAdvice);
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
	
	//删除userAdvcice
	@Override
	public int deleteUserAdviceByAdviceId(String adviceId) {
		int i=0;
		String sql="delete from user_advice where advice_id=?";
		try {
			conn=ConnectionManager.getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, adviceId);
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
