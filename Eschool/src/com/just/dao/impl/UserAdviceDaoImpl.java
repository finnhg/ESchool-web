package com.just.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.just.dao.IUserAdvice;
import com.just.model.UserAdvice;
import com.just.util.ConnectionManager;

public class UserAdviceDaoImpl implements IUserAdvice{
	private static Connection conn; // 数据库连接
	private static PreparedStatement psmt;// 执行数据库查询
	private static ResultSet rs;// 查询结果（数据库结果集的数据表）
	//================================================web端端===================================================
	//分页获取用户意见
	@Override
	public List<UserAdvice> getUserAdviceBypage(int currentpageno, int pageSize) {
		List<UserAdvice> list=new ArrayList<UserAdvice>();
		int beginRecord= (currentpageno - 1) * pageSize;
		int endRecord=pageSize;
		String sql="select * from user_advice limit " + beginRecord + "," + endRecord;
		try {
			conn=ConnectionManager.getConnection();
			psmt=conn.prepareStatement(sql);
			rs=psmt.executeQuery();
			while (rs.next()) {
				UserAdvice userAdvice=new UserAdvice(rs.getString("advice_id"),
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
			ConnectionManager.closeStatement(psmt);
			ConnectionManager.closeResultSet(rs);
		}
		return list;
	}
	//删除意见
	@Override
	public int deleteUserAdvice(String adviceId) {
		int i=0;
		String sql="delete from user_advice where advice_id=?";
		try {
			conn=ConnectionManager.getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, adviceId);
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
	
	//获取总页数
		public static  int getPageCount(int pageSize) {
				int total=0;//总记录数
				int pageCount=0;//总页数
				String sql="select count(*) from user_advice";
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
//================================================android端===================================================
		//添加意见
		@Override
		public int addUserAdvice(UserAdvice userAdvice) {
			//user_id,user_name,advice_keyword,user_advice
			int i=0;
			String sql="INSERT INTO user_advice VALUES(UUID(),?,?,?,?,NOW());";
			try {
				conn=ConnectionManager.getConnection();
				psmt=conn.prepareStatement(sql);
				psmt.setString(1, userAdvice.getUserId());
				psmt.setString(2, userAdvice.getUserName());
				psmt.setString(3, userAdvice.getAdviceKeyword());
				psmt.setString(4, userAdvice.getUserAdvice());
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

}
