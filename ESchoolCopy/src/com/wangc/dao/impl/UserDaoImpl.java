package com.wangc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wangc.dao.IUser;
import com.wangc.model.Collection;
import com.wangc.model.Goods;
import com.wangc.model.Show;
import com.wangc.model.User;
import com.wangc.util.ConnectionManager;

public class UserDaoImpl implements IUser {
	private static Connection conn;// ���ݿ�����
	private static PreparedStatement psmt;// ִ�����ݿ����
	private static ResultSet rs;// ��ѯ�����

	//��ȡuser�����ҳ��
	public static int getPageCount(int pageSize) {
		int totalCount=0;//������
		int pageCount=0;//��ҳ��
		String sql="select count(*) from user";
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
	
	//���ݵ�ǰҳ����ѯusers
	@Override
	public List<User> getUsersByPage(int currentPageNo, int pageSize) {
		List<User> list = new ArrayList<User>();
		int beginRecord = (currentPageNo - 1) * pageSize;
		int endRecord = pageSize;
		String sql = "select user.user_id,user.user_name,user.user_password,user.user_gender,user.user_phone,user.user_school,user.user_birthdate,user.user_signature,user.user_degree,user.user_pic from user limit "
				+ beginRecord + "," + endRecord;
		try {
			PreparedStatement psmt1;
			ResultSet rs1;
			conn = ConnectionManager.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				User user = new User(rs.getString("user_id"),
						rs.getString("user_name"),
						rs.getString("user_password"),
						rs.getString("user_gender"),
						rs.getString("user_phone"),
						rs.getString("user_school"),
						rs.getString("user_birthdate"),
						rs.getString("user_signature"),
						rs.getString("user_pic"), rs.getInt("user_degree"));
				String goodsCountSql = "select count(*) as goodscount from goods where user_id=?";
				String showCountSql = "select count(*) as showcount from shows where user_id=?";
				String collectionCountSql = "select count(*) as collectioncount fron collections where user_id=?";
				int goodsCount = 0;
				int showCount = 0;
				int collectionCount = 0;
				// ��ѯgoods����
				psmt1 = conn.prepareStatement(goodsCountSql);
				psmt1.setString(1, rs.getString("user_id"));
				rs1 = psmt1.executeQuery();
				while (rs1.next()) {
					goodsCount = rs1.getInt("goodscount");
				}
				user.setGoodsCount(goodsCount);
				// ��ѯshow����
				psmt1 = conn.prepareStatement(showCountSql);
				psmt1.setString(1, rs.getString("user_id"));
				rs1 = psmt1.executeQuery();
				while (rs1.next()) {
					showCount = rs1.getInt("showcount");
				}
				user.setShowsCount(showCount);
				// ��ѯcollection����
				psmt1 = conn.prepareStatement(collectionCountSql);
				psmt1.setString(1, rs.getString("user_id"));
				rs1 = psmt1.executeQuery();
				while (rs1.next()) {
					collectionCount=rs1.getInt("collectioncount");
				}
				user.setCollectionsCount(collectionCount);
				list.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(conn);
			ConnectionManager.closeResultSet(rs);
			ConnectionManager.closeStatement(psmt);
		}
		return list;
	}
	//����userId��ѯ������Ʒ
	@Override
	public List<Goods> getGoodsByUserId(int currentPageNo, int pageSize,
			String userId) {
		List<Goods> list=new ArrayList<Goods>();
		int beginRecord=(currentPageNo-1)*pageSize;
		int endRecord=pageSize;
		String sql="SELECT goods.goods_id,goods.goods_name,goods.goods_info,goods.goods_price,"
				+ "goods.goods_pics,goods.goods_time,goods.goods_state,goods.user_id,"
				+ "user.user_name FROM goods,USER WHERE goods.user_id=user.user_id AND "
				+ "goods.user_id=? LIMIT "+ beginRecord + "," + endRecord;
		try {
			conn=ConnectionManager.getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, userId);
			rs=psmt.executeQuery();
			while (rs.next()) {
				Goods goods=new Goods(
						rs.getString("goods_id"),
						rs.getString("goods_name"), 
						rs.getString("goods_info"), 
						rs.getDouble("goods_price"), 
						rs.getString("goods_pics"), 
						rs.getString("goods_time"), 
						rs.getInt("goods_state"), 
						userId, 
						rs.getString("user_name"));
				list.add(goods);
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
	//����userId��ȡ�û�������show
	@Override
	public List<Show> getShowsByUserId(int currentPageNo, int pageSize,
			String userId) {
		List<Show> list=new ArrayList<Show>();
		int beginRecord=(currentPageNo-1)*pageSize;
		int endRecord=pageSize;
		String sql="select shows.show_id,shows.show_message,shows.show_img,shows.show_time,shows.show_praise,shows.user_id,user.user_name from shows,user where shows.user_id=user.user_id and shows.user_id=? limit "
				+ beginRecord + "," + endRecord;
		try {
			ResultSet rs1;
			PreparedStatement psmt1;
			conn=ConnectionManager.getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, userId);
			rs=psmt.executeQuery();
			while (rs.next()) {
				Show show=new Show(
						rs.getString("show_id"), 
						rs.getString("show_message"), 
						rs.getString("show_img"), 
						rs.getString("show_time"), 
						rs.getInt("show_praise"), 
						userId,
						rs.getString("user_name"));
				// ��ѯ��������Ȧ����������
				String commentsql = "select count(*) as comment_count from show_comment where show_id=?";
				int commentCount=0;
				psmt1=conn.prepareStatement(commentsql);
				psmt1.setString(1, userId);
				rs=psmt1.executeQuery();
				while (rs.next()) {
					commentCount=rs.getInt("comment_count");
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
	//�����û�id��ȡuser��collection
	@Override
	public List<Collection> getCollectionsByUserId(int currentpageNo,
			int pageSize, String userId) {
		List<Collection> list=new ArrayList<Collection>();
		int beginRecord=(currentpageNo-1)*pageSize;
		int endRecord=pageSize;
		String sql="SELECT collections.*,goods.goods_pics,goods.goods_name,goods.goods_info,goods.goods_price,goods.goods_time,goods.goods_state,user.user_name FROM collections,goods,USER WHERE collections.goods_id=goods.goods_id AND collections.user_id=? AND user.user_id=? limit "+ beginRecord + "," + endRecord;
		try {
			conn=ConnectionManager.getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, userId);
			psmt.setString(2, userId);
			rs=psmt.executeQuery();
			while (rs.next()) {
				Collection collection=new Collection(
						rs.getString("collection_id"), 
						rs.getString("goods_id"), 
						userId, 
						rs.getString("goods_pics"),
						rs.getString("goods_name"), 
						rs.getString("goods_info"), 
						rs.getDouble("goods_price"), 
						rs.getString("goods_time"), 
						rs.getInt("goods_state"), 
						rs.getString("user_name"));
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
