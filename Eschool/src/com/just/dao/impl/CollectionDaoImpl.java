package com.just.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.just.dao.ICollection;
import com.just.model.Collection;
import com.just.util.ConnectionManager;

/**
 * ICollection接口的实现类
 * @author kobe
 *
 */
public class CollectionDaoImpl implements ICollection{
	private static Connection conn; // 数据库连接
	private static PreparedStatement psmt;// 执行数据库查询
	private static ResultSet rs;// 查询结果（数据库结果集的数据表）
	
	//添加收藏
	@Override
	public int addCollection(String userId, String goodsId) {
		int i=0;
		String sql="INSERT INTO collections VALUE(UUID(),?,?);";
		try {
			conn=ConnectionManager.getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, userId);
			psmt.setString(2, goodsId);
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
	//取消收藏
	@Override
	public int cancelCollection(String userId, String goodsId) {
		int i=0;
		String sql="DELETE FROM collections WHERE user_id=? AND goods_id=?";
		try {
			conn=ConnectionManager.getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, userId);
			psmt.setString(2, goodsId);
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
	//根据userId获取所有收藏
	@Override
	public List<Collection> getCollectionsByUserId(String userId) {
		List<Collection> list=new ArrayList<Collection>();
		String sql="SELECT * FROM collections WHERE user_id=?";
		try {
			conn=ConnectionManager.getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, userId);
			rs=psmt.executeQuery();
			while (rs.next()) {
				Collection collection=new Collection(rs.getString("collection_id"),
						rs.getString("goods_id"), 
						userId);
				list.add(collection);
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

}
