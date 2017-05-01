package com.just.dao;

import java.util.List;

import com.just.model.Collection;
import com.just.model.Goods;
import com.just.model.Show;
import com.just.model.User;

public interface IUser {
	// ====================web端===============================
	// web端分页查询用户
	List<User> getUsersBypage(int currentpageno, int pageSize);

	// 根据useId获取该user的所有商品
	List<Goods> getGoodsByUserId(int currentPageNo, int pageSize, String userId);

	// 根据userId获取该user的所有朋友圈
	List<Show> getShowByUserId(int currentpageno, int pageSize, String userId);

	// 根据userId获取该user的所有收藏
	List<Collection> getCollectionsByUserId(int currentpageno, int pageSize,
			String userId);

	// ========================android端============================

	// 验证用户是否已经注册
	int checkUserExist(String userPhone);

	// 注册
	int register(String userPhone, String userPwd);

	// 获取注册成功后数据库生成的userId
	String getUserIdByUserPhone(String userPhone);

	// 登录（电话，密码）
	List<User> getUserByPhonePwd(String user_phone, String user_password);

	// 登录（短信验证登录）
	List<User> getUserByPhone(String user_phone);

	// 上传头像名称
	int uploadPicName(String user_id, String user_pic);

	// 修改用户名
	int updateUserName(String userId, String userName);

	// 修改用户签名
	int updateUserSignature(String userId, String userSignature);

	// 修改性别
	int updateUserGender(String userId, String userGender);

	// 修改生日
	int updateUserBirthdate(String userId, String userBirthdate);

	// 修改学校
	int updateUserSchool(String userId, String userSchool);

	// 修改密码
	int updateUserPassword(String userId, String userPassword);

	// 根据userId获取userPic
	String getUserPicByUserId(String userId);

	// 根据userId获取userDegree
	int getUserDegreeByUserId(String userId);
	
	//更具userId获取User
	User getUserByUserId(String userId);
}
