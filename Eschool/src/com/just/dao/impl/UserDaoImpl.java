package com.just.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.just.dao.IUser;
import com.just.model.Collection;
import com.just.model.Goods;
import com.just.model.Show;
import com.just.model.User;
import com.just.util.ConnectionManager;

public class UserDaoImpl implements IUser {
	private static Connection conn; // 数据库连接
	private static PreparedStatement psmt;// 执行数据库查询
	private static ResultSet rs;// 查询结果（数据库结果集的数据表）

	// =====================================web端=====================================================
	// web端分页查询所有用户
	@Override
	public List<User> getUsersBypage(int currentpageno, int pageSize) {
		List<User> list = new ArrayList<User>();
		int beginRecord = (currentpageno - 1) * pageSize;
		int endRecord = pageSize;
		String sql = "select user.user_id,user.user_name,user.user_password,user.user_gender,user.user_phone,user.user_school,user.user_birthdate,user.user_signature,user.user_degree,user.user_pic from user limit "
				+ beginRecord + "," + endRecord;
		try {
			ResultSet rs1;
			PreparedStatement psmt1;
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
						rs.getInt("user_degree"), 
						rs.getString("user_pic"));
				String goodssql = "SELECT COUNT(*) AS goods_count  FROM goods WHERE user_id=?";
				String showsql = "SELECT COUNT(*) AS show_count FROM shows WHERE user_id=?";
				String collectionsql = "SELECT COUNT(*) AS collect_count FROM collections WHERE user_id=?";
				int goods_count = 0;
				int show_count = 0;
				int collection_count = 0;
				// 查询goods数量
				psmt1 = conn.prepareStatement(goodssql);
				psmt1.setString(1, rs.getString("user_id"));
				rs1 = psmt1.executeQuery();
				while (rs1.next()) {
					goods_count = rs1.getInt("goods_count");
				}
				user.setGoodsCount(goods_count);
				// 查询show数量
				psmt1 = conn.prepareStatement(showsql);
				psmt1.setString(1, rs.getString("user_id"));
				rs1 = psmt1.executeQuery();
				while (rs1.next()) {
					show_count = rs1.getInt("show_count");
				}
				user.setShowsCount(show_count);
				// 查询collection数量
				psmt1 = conn.prepareStatement(collectionsql);
				psmt1.setString(1, rs.getString("user_id"));
				rs1 = psmt1.executeQuery();
				while (rs1.next()) {
					collection_count = rs1.getInt("collect_count");
				}
				user.setCollectionsCount(collection_count);

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

	// 根据用户id获取用户名
	public static String getUserNameByUserId(String userId) {
		String userName = null;
		String sql = "select user_name from user where user_id=?";
		try {
			conn = ConnectionManager.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userId);
			rs = psmt.executeQuery();
			while (rs.next()) {
				userName = rs.getString("user_name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(conn);
			ConnectionManager.closeResultSet(rs);
			ConnectionManager.closeStatement(psmt);
		}
		return userName;
	}

	// 获取所有用户总页数
	public static int getPageCount(int pageSize) {
		int total = 0;// 总记录数
		int pageCount = 0;// 总页数
		String sql = "select count(*) from user";
		try {
			conn = ConnectionManager.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				total = rs.getInt(1);
				pageCount = (total - 1) / pageSize + 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(conn);
			ConnectionManager.closeResultSet(rs);
			ConnectionManager.closeStatement(psmt);
		}
		return pageCount;
	}

	// 获取某个用户的商品总页数
	public static int getGoodsPageCountByUserId(int pageSize, String userId) {
		int total = 0;// 总记录数
		int pageCount = 0;// 总页数
		String sql = "select count(*) from goods where user_id=?";
		try {
			conn = ConnectionManager.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userId);
			rs = psmt.executeQuery();
			while (rs.next()) {
				total = rs.getInt(1);
				pageCount = (total - 1) / pageSize + 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(conn);
			ConnectionManager.closeResultSet(rs);
			ConnectionManager.closeStatement(psmt);
		}
		return pageCount;
	}

	// 获取某个用户的朋友圈总页数
	public static int getShowsPageCountByUserId(int pageSize, String userId) {
		int total = 0;// 总记录数
		int pageCount = 0;// 总页数
		String sql = "select count(*) from shows where user_id=?";
		try {
			conn = ConnectionManager.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userId);
			rs = psmt.executeQuery();
			while (rs.next()) {
				total = rs.getInt(1);
				pageCount = (total - 1) / pageSize + 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(conn);
			ConnectionManager.closeResultSet(rs);
			ConnectionManager.closeStatement(psmt);
		}
		return pageCount;
	}

	// 获取某个用户的收藏总页数
	public static int getCollectionPageCountByUserId(int pageSize, String userId) {
		int total = 0;// 总记录数
		int pageCount = 0;// 总页数
		String sql = "select count(*) from collections where user_id=?";
		try {
			conn = ConnectionManager.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userId);
			rs = psmt.executeQuery();
			while (rs.next()) {
				total = rs.getInt(1);
				pageCount = (total - 1) / pageSize + 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(conn);
			ConnectionManager.closeResultSet(rs);
			ConnectionManager.closeStatement(psmt);
		}
		return pageCount;
	}

	// 根据用户id获取发布的goods
	@Override
	public List<Goods> getGoodsByUserId(int currentpageno, int pageSize,
			String userId) {
		List<Goods> list = new ArrayList<Goods>();
		int beginRecord = (currentpageno - 1) * pageSize;
		int endRecord = pageSize;
		String sql = "SELECT goods.goods_id,goods.goods_name,goods.goods_info,goods.goods_price,"
				+ "goods.goods_pics,goods.goods_time,goods.goods_state,goods.user_id,"
				+ "user.user_name FROM goods,USER WHERE goods.user_id=user.user_id AND "
				+ "goods.user_id=? LIMIT "+ beginRecord + "," + endRecord;
		try {
			conn = ConnectionManager.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userId);
			rs = psmt.executeQuery();
			while (rs.next()) {
				Goods goods = new Goods(
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
		} finally {
			ConnectionManager.closeConnection(conn);
			ConnectionManager.closeResultSet(rs);
			ConnectionManager.closeStatement(psmt);
		}
		return list;
	}

	// 根据用户id获取发布的show
	@Override
	public List<Show> getShowByUserId(int currentpageno, int pageSize,String userId) {
		List<Show> list = new ArrayList<Show>();
		int beginRecord = (currentpageno - 1) * pageSize;
		int endRecord = pageSize;
		String sql = "select shows.show_id,shows.show_message,shows.show_img,shows.show_time,shows.show_praise,shows.user_id,user.user_name from shows,user where shows.user_id=user.user_id and shows.user_id=? limit "
				+ beginRecord + "," + endRecord;
		try {
			ResultSet rs1;
			PreparedStatement psmt1;
			conn = ConnectionManager.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userId);
			rs = psmt.executeQuery();
			while (rs.next()) {
				Show show = new Show(rs.getString("show_id"),
						rs.getString("show_message"), rs.getString("show_img"),
						rs.getString("show_time"), rs.getInt("show_praise"),
						userId, rs.getString("user_name"));
				// 查询该条朋友圈的评论数量
				String commentsql = "select count(*) as comment_count from show_comment where show_id=?";
				int commentCount = 0;
				psmt1 = conn.prepareStatement(commentsql);
				psmt1.setString(1, rs.getString("show_id"));
				rs1 = psmt1.executeQuery();
				while (rs1.next()) {
					commentCount = rs1.getInt("comment_count");
				}
				show.setCommentCount(commentCount);
				list.add(show);
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

	// 根据用户id获取用户的collection
	@Override
	public List<Collection> getCollectionsByUserId(int currentpageno,int pageSize, String userId) {
		List<Collection> list=new ArrayList<Collection>();
		int beginRecord = (currentpageno - 1) * pageSize;
		int endRecord = pageSize;
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

	
	
	
	
	
	// ======================================android端====================================================
	
	/**
	 * 检查该号码是否已经注册过
	 */
	@Override
	public int checkUserExist(String userPhone) {
		int i=0;
		String sql="SELECT COUNT(*) FROM USER WHERE user_phone=?";
		try {
			conn=ConnectionManager.getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, userPhone);
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

	/**
	 * 注册
	 */
	@Override
	public int register(String userPhone, String userPwd) {
		int i=0;
		String sql="INSERT INTO USER (user_id,user_phone,user_password) VALUES (UUID(),?,?)";
		try {
			conn=ConnectionManager.getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, userPhone);
			psmt.setString(2, userPwd);
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
	
	/**
	 * 获取注册成功后数据库生成的userId
	 * @param userPhone 传入电话号码
	 */
	@Override
	public String getUserIdByUserPhone(String userPhone) {
		String userId = null;
		String sql="SELECT user_id FROM USER WHERE user_phone=?";
		try {
			conn=ConnectionManager.getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, userPhone);
			rs=psmt.executeQuery();
			while (rs.next()) {
				userId=rs.getString(1);
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
	
	/**
	 * 登录
	 * @param userPhone 号码
	 * @param userPassword 登录密码
	 */
	@Override
	public List<User> getUserByPhonePwd(String userPhone, String userPassword) {
		List<User> list = new ArrayList<User>();
		try {
			conn = ConnectionManager.getConnection();
			String sql = "SELECT * FROM USER WHERE user_phone=? AND user_password=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userPhone);
			psmt.setString(2, userPassword);
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
						rs.getInt("user_degree"), 
						rs.getString("user_pic"));
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
	
	//短信验证登录
	@Override
	public List<User> getUserByPhone(String userPhone) {
		List<User> list = new ArrayList<User>();
		try {
			conn = ConnectionManager.getConnection();
			String sql = "SELECT * FROM USER WHERE user_phone=? ";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userPhone);
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
						rs.getInt("user_degree"), 
						rs.getString("user_pic"));
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

	/**
	 * 上传头像名到数据库
	 */
	@Override
	public int uploadPicName(String user_id, String user_pic) {
		int i = 0;
		String sql = "UPDATE USER SET user_pic=? WHERE user_id=?";
		try {
			conn = ConnectionManager.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, user_pic);
			psmt.setString(2, user_id);
			i = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	/**
	 * 修改用户名
	 */
	@Override
	public int updateUserName(String userId, String userName) {
		int i=0;
		String sql="UPDATE USER SET user_name=? WHERE user_id=?";
		String sql1="UPDATE goods SET user_name=? WHERE user_id=?";
		PreparedStatement psmt1;
		try {
			conn=ConnectionManager.getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, userName);
			psmt.setString(2, userId);
			i=psmt.executeUpdate();
			
			psmt1=conn.prepareStatement(sql1);
			psmt1.setString(1, userName);
			psmt1.setString(2, userId);
			int i1=psmt1.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionManager.closeConnection(conn);
			ConnectionManager.closeResultSet(rs);
			ConnectionManager.closeStatement(psmt);
		}
		return i;
	}
	/**
	 * 修改个性签名
	 */
	@Override
	public int updateUserSignature(String userId, String userSignature) {
		int i=0;
		String sql="UPDATE USER SET user_signature=? WHERE user_id=?";
		try {
			conn=ConnectionManager.getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, userSignature);
			psmt.setString(2, userId);
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
	/**
	 * 修改性别
	 */
	@Override
	public int updateUserGender(String userId, String userGender) {
		int i=0;
		String sql="UPDATE USER SET user_gender=? WHERE user_id=?";
		try {
			conn=ConnectionManager.getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, userGender);
			psmt.setString(2, userId);
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
	/**
	 * 修改出生日期
	 */
	@Override
	public int updateUserBirthdate(String userId, String userBirthdate) {
		int i=0;
		String sql="UPDATE USER SET user_birthdate=? WHERE user_id=?";
		try {
			conn=ConnectionManager.getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, userBirthdate);
			psmt.setString(2, userId);
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
	/**
	 * 修改学校
	 */
	@Override
	public int updateUserSchool(String userId, String userSchool) {
		int i=0;
		String sql="UPDATE USER SET user_school=? WHERE user_id=?";
		try {
			conn=ConnectionManager.getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, userSchool);
			psmt.setString(2, userId);
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
	/**
	 * 修改密码
	 */
	@Override
	public int updateUserPassword(String userId, String userPassword) {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * 根据userId获取userPic
	 */
	@Override
	public String getUserPicByUserId(String userId) {
		String userPic = null;
		String sql="SELECT user_pic FROM USER WHERE user_id=? ";
		try {
			conn=ConnectionManager.getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, userId);
			rs=psmt.executeQuery();
			while (rs.next()) {
				userPic=rs.getString("user_pic");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionManager.closeConnection(conn);
			ConnectionManager.closeResultSet(rs);
			ConnectionManager.closeStatement(psmt);
		}
		return userPic;
	}
	/**
	 * 根据userId获取userDegree
	 */
	@Override
	public int getUserDegreeByUserId(String userId) {
		int i=0;
		String sql="SELECT user_degree FROM USER WHERE user_id=? ";
		try {
			conn=ConnectionManager.getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, userId);
			rs=psmt.executeQuery();
			while (rs.next()) {
				i=rs.getInt("user_degree");
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
	//更具userId获取User
	@Override
	public User getUserByUserId(String userId) {
		User user=null;
		String sql="SELECT * FROM USER WHERE user_id=?";
		try {
			conn=ConnectionManager.getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, userId);
			rs=psmt.executeQuery();
			while (rs.next()) {
				user=new User(userId, 
						rs.getString("user_name"), 
						rs.getString("user_gender"), 
						rs.getString("user_phone"), 
						rs.getString("user_school"), 
						rs.getString("user_birthdate"), 
						rs.getString("user_signature"), 
						rs.getInt("user_degree"), 
						rs.getString("user_pic"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionManager.closeConnection(conn);
			ConnectionManager.closeResultSet(rs);
			ConnectionManager.closeStatement(psmt);
		}
		return user;
	}


}
