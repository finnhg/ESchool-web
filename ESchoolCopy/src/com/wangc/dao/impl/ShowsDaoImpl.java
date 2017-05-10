package com.wangc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wangc.dao.IShows;
import com.wangc.model.Show;
import com.wangc.util.ConnectionManager;

public class ShowsDaoImpl implements IShows{
	static Connection conn=null;
	 static PreparedStatement psmt;
	 static ResultSet rs;
	
	public static int getPageCount(int pageSize) {
		int pageCount=0;
		String sql="select count(*) from shows";
		try {
			conn=ConnectionManager.getConnection();
			psmt=conn.prepareStatement(sql);
			rs=psmt.executeQuery();
			while (rs.next()) {
				pageCount=(rs.getInt(1)-1)/pageSize+1;
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
	
	//∑÷“≥ªÒ»°Shows
	@Override
	public List<Show> getShowsListBypageNo(int currentPageNo, int pageSize) {
		List<Show> list = new ArrayList<Show>();
		int beginRecord=(currentPageNo-1)*pageSize;
		int endRecord = pageSize;
		String sql="select shows.*,user.user_name from shows left join user on shows.user_id=user.user_id  limit ?,? ";
		try {
			conn=ConnectionManager.getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, beginRecord);
			psmt.setInt(2, endRecord);
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
	//…æ≥˝Shows
	@Override
	public int deleteShowByShowId(String showId) {
		int i = 0;
		String sql="delete from shows where show_id=? ";
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
