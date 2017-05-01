package com.just.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.just.dao.IShowComment;
import com.just.model.ShowComment;
import com.just.util.ConnectionManager;

public class ShowCommentDaoImpl implements IShowComment{
	static Connection conn=null;
	static PreparedStatement psmt=null;
	static ResultSet rs=null;
	//===========================================web端=====================================================
	//根据showId查询该朋友圈的所有评论
	@Override
	public List<ShowComment> getShowCommentByShowId(int currentpageno,int pageSize, String showId) {
		List<ShowComment> list=new ArrayList<ShowComment>();
		int beginRecord = (currentpageno - 1) * pageSize;
		int endRecord = pageSize;
		String sql="SELECT * FROM show_comment WHERE show_id=? limit " + beginRecord+" , "+endRecord;
		try {
			conn=ConnectionManager.getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, showId);
			rs=psmt.executeQuery();
			while (rs.next()) {
				ShowComment showComment=new ShowComment(
						rs.getString("show_comment_id"), 
						showId, 
						rs.getString("user_id"), 
						rs.getString("commenter_id"), 
						rs.getString("commenter_name"), 
						rs.getString("commenter_img"),
						rs.getString("comment_time"), 
						rs.getString("show_comment_message"));
				list.add(showComment);
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
	
	//删除某条评论
	@Override
	public int deleteShowComment(String showCommentId) {
		int i=0;
		String sql="delete from show_comment where show_comment_id=?";
		try {
			conn=ConnectionManager.getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, showCommentId);
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
	
	//获取某条朋友圈评论的总页数
	public static int  getShowCommentPageCount(int pageSize,String showId) {
		int total=0;//总条数
		int pageCount=0;//总页数
		String sql="select count(*) from show_comment where show_id=? ";
		try {
			conn=ConnectionManager.getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, showId);
			rs=psmt.executeQuery();
			while (rs.next()) {
				total=rs.getInt(1);
				pageCount=(total-1)/pageSize+1;
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
	
	
	//根据show_comment_id获取show_id
		public static  String getShowIdByShowCommentId(String showCommentId) {
			String sql="SELECT show_id FROM show_comment WHERE show_comment_id=?";
			String showId=null;
			try {
				conn=ConnectionManager.getConnection();
				psmt=conn.prepareStatement(sql);
				psmt.setString(1, showCommentId);
				rs=psmt.executeQuery();
				while (rs.next()) {
					showId=rs.getString("show_id");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				ConnectionManager.closeConnection(conn);
				ConnectionManager.closeResultSet(rs);
				ConnectionManager.closeStatement(psmt);
			}
			return showId;
		}
		
		//===========================================android端=====================================================
		//查询所有评论
		@Override
		public List<ShowComment> getShowComments(String showId) {
			List<ShowComment> list=new ArrayList<ShowComment>();
			String sql="SELECT * FROM show_comment WHERE show_id=? ORDER BY comment_time DESC";
			try {
				conn=ConnectionManager.getConnection();
				psmt=conn.prepareStatement(sql);
				psmt.setString(1, showId);
				rs=psmt.executeQuery();
				while (rs.next()) {
					ShowComment showComment=new ShowComment(rs.getString("show_comment_id"), 
							showId, 
							rs.getString("user_id"), 
							rs.getString("commenter_id"),
							rs.getString("commenter_name"), 
							rs.getString("commenter_img"),
							rs.getString("comment_time"), 
							rs.getString("show_comment_message"));
					list.add(showComment);
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
		//查询评论的数量
		@Override
		public int getCommentCount(String showId) {
			int i=0;
			String sql="select count(*) from show_comment where show_id=?";
			try {
				conn=ConnectionManager.getConnection();
				psmt=conn.prepareStatement(sql);
				psmt.setString(1, showId);
				rs=psmt.executeQuery();
				while (rs.next()) {
					i=rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				ConnectionManager.closeConnection(conn);
				ConnectionManager.closeResultSet(rs);
				ConnectionManager.closeStatement(psmt);
			}
			return i;
		}
		//添加朋友圈评论
		@Override
		public int addShowComment(ShowComment showComment) {
			int i=0;
			String sql="INSERT INTO show_comment VALUE(UUID(),?,?,?,?,?,NOW(),?);";
			try {
				conn=ConnectionManager.getConnection();
				psmt=conn.prepareStatement(sql);
				psmt.setString(1, showComment.getShowId());
				psmt.setString(2, showComment.getUserId());
				psmt.setString(3, showComment.getCommenterId());
				psmt.setString(4, showComment.getCommenterName());
				psmt.setString(5, showComment.getCommenterImg());
				psmt.setString(6, showComment.getShowCommentMessage());
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
