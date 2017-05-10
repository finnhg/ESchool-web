package com.wangc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wangc.dao.IShowComment;
import com.wangc.model.ShowComment;
import com.wangc.util.ConnectionManager;

public class ShowCommentDaoImpl implements IShowComment{
	private static Connection conn;
	private static PreparedStatement psmt;
	private static ResultSet rs;
	
	//ªÒ»°≈Û”—»¶µƒ∆¿¬€
	@Override
	public List<ShowComment> getShowCommentByShowId(int currentPageNo,int pageSize, String showId) {
		List<ShowComment> list = new ArrayList<ShowComment>();
		int beginRecord=0;
		int endRecord=pageSize;
		beginRecord=(currentPageNo-1)*pageSize;
		String sql="Select * from show_comment limit ?,?";
		try {
			conn=ConnectionManager.getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, beginRecord);
			psmt.setInt(2, endRecord);
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

	//…æ≥˝≈Û”—»¶
	@Override
	public int deleteShowComment(String showCommentId) {
		int i = 0;
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

}
