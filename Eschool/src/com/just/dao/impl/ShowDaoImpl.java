package com.just.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.just.dao.IShows;
import com.just.model.Show;
import com.just.model.ShowComment;
import com.just.util.ConnectionManager;

public class ShowDaoImpl implements IShows{
	static Connection conn=null;
	static PreparedStatement psmt=null;
	static ResultSet rs=null;
	//====================================================web端==========================================================
	//web端分页显示朋友圈
	@Override
	public List<Show> getShowsBypage(int currentpageno, int pageSize) {
		List<Show> list=new ArrayList<Show>();
		int beginRecord= (currentpageno - 1) * pageSize;
		int endRecord=pageSize;
		String sql="select shows.show_id,shows.show_message,shows.show_img,shows.show_time,shows.show_praise,shows.user_id,user.user_name from shows,user where shows.user_id=user.user_id limit "+ beginRecord + "," + endRecord;
		try {
			ResultSet rs1;
			PreparedStatement psmt1;
			conn=ConnectionManager.getConnection();
			psmt=conn.prepareStatement(sql);
			rs=psmt.executeQuery();
			while (rs.next()) {
				Show show=new Show(
						rs.getString("show_id"), 
						rs.getString("show_message"), 
						rs.getString("show_img"),
						rs.getString("show_time"), 
						rs.getInt("show_praise"), 
						rs.getString("user_id"),
						rs.getString("user_name"));
				//查询该条朋友圈的评论数量
				String commentsql="select count(*) as comment_count from show_comment where show_id=?";
				int commentCount=0;
				psmt1=conn.prepareStatement(commentsql);
				psmt1.setString(1, rs.getString("show_id"));
				rs1=psmt1.executeQuery();
				while (rs1.next()) {
					commentCount=rs1.getInt("comment_count");
				}
				show.setCommentCount(commentCount);
				list.add(show);
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
	//根据show_id获取user_id
		public static  String getUserIdByShowId(String showId) {
			String sql="SELECT user_id FROM shows WHERE show_id=?";
			String userId=null;
			try {
				conn=ConnectionManager.getConnection();
				psmt=conn.prepareStatement(sql);
				psmt.setString(1, showId);
				rs=psmt.executeQuery();
				while (rs.next()) {
					userId=rs.getString("user_id");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				ConnectionManager.closeConnection(conn);
				ConnectionManager.closeResultSet(rs);
				ConnectionManager.closeStatement(psmt);
			}
			return userId;
		}
		
	//删除show(与android端共用)
	@Override
	public int deleteShows(String showId) {
		int i=0;
		String sql="delete from shows where show_id=?";
		try {
			conn=ConnectionManager.getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, showId);
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
	
	//获取总页数
	public static  int getPageCount(int pageSize) {
		int total=0;//总记录数
		int pageCount=0;//总页数
		String sql="select count(*) from shows";
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


	
//=============================================android端================================================================
	//添加show
	@Override
	public int addShow(Show show) {
		int i=0;
		String sql="INSERT INTO shows VALUE(UUID(),?,?,NOW(),0,?);";
		try {
			conn=ConnectionManager.getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, show.getShowMessage());
			psmt.setString(2, show.getShowImg());
			psmt.setString(3, show.getUserId());
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
	//获取所有show
	@Override
	public List<Show> getAllShow() {
		List<Show> list=new ArrayList<Show>();
		String sql="select shows.show_id,shows.show_message,shows.show_img,shows.show_time,shows.show_praise,shows.user_id,user.user_name from shows,user where shows.user_id=user.user_id ORDER BY show_time DESC";
		try {
			conn=ConnectionManager.getConnection();
			psmt=conn.prepareStatement(sql);
			rs=psmt.executeQuery();
			while (rs.next()) {
				Show show=new Show(rs.getString("show_id"), 
						rs.getString("show_message"), 
						rs.getString("show_img"), 
						rs.getString("show_time"), 
						rs.getInt("show_praise"), 
						rs.getString("user_id"),
						rs.getString("user_name"));
				list.add(show);
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
	//获取my show
	@Override
	public List<Show> getMyShow(String userId) {
		List<Show> list=new ArrayList<Show>();
		String sql="SELECT * FROM shows WHERE user_id=? ORDER BY show_time DESC";
		try {
			ResultSet rs1;
			PreparedStatement psmt1;
			conn=ConnectionManager.getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, userId);
			rs=psmt.executeQuery();
			while (rs.next()) {
				Show show=new Show(rs.getString("show_id"), 
						rs.getString("show_message"), 
						rs.getString("show_img"), 
						rs.getString("show_time"), 
						rs.getInt("show_praise"), 
						rs.getString("user_id"));
				String sql1="select user_name from user where user_id=?";
				String userName=null;
				psmt1=conn.prepareStatement(sql1);
				psmt1.setString(1, userId);
				rs1=psmt1.executeQuery();
				while (rs1.next()) {
					userName=rs1.getString("user_name");
				}
				show.setUserName(userName);
				list.add(show);
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
	//给show点赞（更新show的点赞数量）
	@Override
	public int updateShowPraise(String showId) {
		int i=0;
		String sql="UPDATE shows SET show_praise=show_praise+1 WHERE show_id=?";
		try {
			conn=ConnectionManager.getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, showId);
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
