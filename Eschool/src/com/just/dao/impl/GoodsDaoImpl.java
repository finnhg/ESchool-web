package com.just.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.just.dao.IGoods;
import com.just.model.Goods;
import com.just.util.ConnectionManager;

public class GoodsDaoImpl implements IGoods{
	private static Connection conn; // 数据库连接
	private static PreparedStatement psmt;// 执行数据库查询
	private static ResultSet rs;// 查询结果（数据库结果集的数据表）
	
	//===============================================web端=================================================================
	//删除商品(与android端共用)
	@Override
	public int deleteGoods(String goodsId) {
		int i=0;
		String sql="delete from goods where goods_id=?";
		try {
			conn=ConnectionManager.getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, goodsId);
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

	
	//分页查询商品
	@Override
	public List<Goods> getGoodsBypage(int currentpageno, int pageSize) {
		List<Goods> list=new ArrayList<Goods>();
		int beginRecord= (currentpageno - 1) * pageSize;
		int endRecord=pageSize;
		String sql = "select goods.goods_id,goods.goods_name,goods.goods_info,goods.goods_price,goods.goods_pics,goods.goods_time,goods.goods_state,goods.user_id,user.user_name from goods,user where goods.user_id=user.user_id limit " + beginRecord + ","+ endRecord;
		try {
			conn=ConnectionManager.getConnection();
			psmt=conn.prepareStatement(sql);
			rs=psmt.executeQuery();
			while (rs.next()) {
				Goods goods=new Goods(rs.getString("goods_id"),
						rs.getString("goods_name"),
						rs.getString("goods_info"), 
						rs.getDouble("goods_price"),
						rs.getString("goods_pics"), 
						rs.getString("goods_time"), 
						rs.getInt("goods_state"),
						rs.getString("user_id"),
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
	//根据goods_id获取user_id
	public static  String getUserIdByGoodsId(String goodsId) {
		String sql="SELECT user_id FROM goods WHERE goods_id=?";
		String userId=null;
		try {
			conn=ConnectionManager.getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, goodsId);
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
	
	//获取总页数
	public static  int getPageCount(int pageSize) {
			int total=0;//总记录数
			int pageCount=0;//总页数
			String sql="select count(*) from goods";
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
	
	
	//==============================================android端==================================================================
	//添加商品
	@Override
	public int addGoods(Goods goods) {
		int i=0;
		String sql="insert into goods values(UUID(),?,?,?,?,NOW(),0,?,?) ";
		try {
			conn=ConnectionManager.getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, goods.getGoodsName());
			psmt.setString(2, goods.getGoodsInfo());
			psmt.setDouble(3, goods.getGoodsPrice());
			psmt.setString(4, goods.getGoodsPic());
			psmt.setString(5, goods.getUserId());
			psmt.setString(6, goods.getUserName());
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

	//更新商品状态（是否已经卖出,用户等级加1）
	@Override
	public int updateGoodsState(String userId,String goodsId) {
		int i1=0;
		int i2=0;
		//更新商品状态的同时，用户的信用等级加1
		String sql1="update goods set goods_state=1 where goods_id=? ";
		String sql2="UPDATE USER SET user_degree=user_degree+1 WHERE user_id=?";
		PreparedStatement psmt2 = null;
		try {
			conn=ConnectionManager.getConnection();
			psmt=conn.prepareStatement(sql1);
			psmt.setString(1, goodsId);
			i1=psmt.executeUpdate();
			
			psmt2=conn.prepareStatement(sql2);
			psmt2.setString(1, userId);
			i2=psmt2.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionManager.closeConnection(conn);
			ConnectionManager.closeResultSet(rs);
			ConnectionManager.closeStatement(psmt);
			ConnectionManager.closeStatement(psmt2);
		}
		return i1;
	}
	//根据用户id获取发布goods
	@Override
	public List<Goods> getPublishGoodsByUserId(String userId) {
		List<Goods> list=new ArrayList<Goods>();
		String sql="select * from goods where user_id=? ORDER BY goods_time DESC";
		try {
			conn=ConnectionManager.getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, userId);
			rs=psmt.executeQuery();
			while (rs.next()) {
				Goods goods=new Goods(rs.getString("goods_id"), 
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
	
	 // 根据用户id获取收藏的goods
	@Override
	public List<Goods> getCollectionGoodsByUserId(String userId) {
		List<Goods> list=new ArrayList<Goods>();
		//多表查询，先根据userId获取goodsId，然后获取goods
		String sql="select * from collections where user_id=? ";
		String sql1="select * from goods where goods_id=?";
		try {
			String goods_id=null;
			ResultSet rs1;
			PreparedStatement psmt1;
			conn=ConnectionManager.getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, userId);
			rs=psmt.executeQuery();
			while (rs.next()) {
				goods_id=rs.getString("goods_id");
				psmt1=conn.prepareStatement(sql1);
				psmt1.setString(1, goods_id);
				rs1=psmt1.executeQuery();
				while (rs1.next()) {
					Goods goods=new Goods(goods_id, 
							rs1.getString("goods_name"),
							rs1.getString("goods_info"),
							rs1.getDouble("goods_price"), 
							rs1.getString("goods_pics"), 
							rs1.getString("goods_time"), 
							rs1.getInt("goods_state"), 
							userId, 
							rs1.getString("user_name"));
					list.add(goods);
				}
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
	//获取所有goods
	@Override
	public List<Goods> getAllGoods() {
		List<Goods> list=new ArrayList<Goods>();
		String sql="SELECT * FROM goods ORDER BY goods_time DESC";
		try {
			conn=ConnectionManager.getConnection();
			psmt=conn.prepareStatement(sql);
			rs=psmt.executeQuery();
			while (rs.next()) {
				Goods goods=new Goods(rs.getString("goods_id"), 
						rs.getString("goods_name"),
						rs.getString("goods_info"),
						rs.getDouble("goods_price"),
						rs.getString("goods_pics"),
						rs.getString("goods_time"), 
						rs.getInt("goods_state"), 
						rs.getString("user_id"), 
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

	//获取goods State
	@Override
	public int getGoodsState(String goodsId) {
		int i=0;
		String sql="SELECT goods_state FROM goods WHERE goods_id=?";
		try {
			conn=ConnectionManager.getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, goodsId);
			rs=psmt.executeQuery();
			while (rs.next()) {
				i=rs.getInt("goods_state");
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
	//搜索goods
	@Override
	public List<Goods> getSearchGoods(String keyWord) {
		List<Goods> list=new ArrayList<Goods>();
		String sql="SELECT * FROM goods WHERE goods_name like concat('%',?,'%') ORDER BY goods_time DESC";
		try {
			conn=ConnectionManager.getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, keyWord);
			rs=psmt.executeQuery();
			while (rs.next()) {
				Goods goods=new Goods(rs.getString("goods_id"), 
						rs.getString("goods_name"),
						rs.getString("goods_info"),
						rs.getDouble("goods_price"),
						rs.getString("goods_pics"),
						rs.getString("goods_time"), 
						rs.getInt("goods_state"), 
						rs.getString("user_id"), 
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
	

	
	
}
