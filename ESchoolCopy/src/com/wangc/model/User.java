package com.wangc.model;

import java.sql.Date;

/**
 * 用户类
 * @author wangc
 *
 */
public class User {
	private String userId;//userId
	private String userName;//user姓名
	private String userPwd;//user密码
	private String userGender;//user性别
	private String userPhone;//user电话
	private String userSchool;//user学校
	private String userBirthdate;//user出生日期
	private String userSignature;//user个性签名
	private String userPic;//用户头像
	private int userDegree;//user信用等级
	private int goodsCount;//user发布的商品数量
	private int showsCount;//user的朋友圈数量
	private int collectionsCount;//user的收藏数量
	//无参构造方法
	public User() {
		super();
	}
	
	public User(String userId, String userName, String userPwd,
			String userGender, String userPhone, String userSchool,
			String userBirthdate, String userSignature, String userPic,
			int userDegree) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPwd = userPwd;
		this.userGender = userGender;
		this.userPhone = userPhone;
		this.userSchool = userSchool;
		this.userBirthdate = userBirthdate;
		this.userSignature = userSignature;
		this.userPic = userPic;
		this.userDegree = userDegree;
	}



	//有参构造方法
	public User(String userId, String userName, String userPwd,
			String userGender, String userPhone, String userSchool,
			String userBirthdate, String userSignature, String userPic,
			int userDegree, int goodsCount, int showsCount, int collectionsCount) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPwd = userPwd;
		this.userGender = userGender;
		this.userPhone = userPhone;
		this.userSchool = userSchool;
		this.userBirthdate = userBirthdate;
		this.userSignature = userSignature;
		this.userPic = userPic;
		this.userDegree = userDegree;
		this.goodsCount = goodsCount;
		this.showsCount = showsCount;
		this.collectionsCount = collectionsCount;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getGoodsCount() {
		return goodsCount;
	}
	public void setGoodsCount(int goodsCount) {
		this.goodsCount = goodsCount;
	}
	public int getShowsCount() {
		return showsCount;
	}
	public void setShowsCount(int showsCount) {
		this.showsCount = showsCount;
	}
	public int getCollectionsCount() {
		return collectionsCount;
	}
	public void setCollectionsCount(int collectionsCount) {
		this.collectionsCount = collectionsCount;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserGender() {
		return userGender;
	}
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserSchool() {
		return userSchool;
	}
	public void setUserSchool(String userSchool) {
		this.userSchool = userSchool;
	}
	public String getUserBirthdate() {
		return userBirthdate;
	}
	public void setUserBirthdate(String userBirthdate) {
		this.userBirthdate = userBirthdate;
	}
	public String getUserSignature() {
		return userSignature;
	}
	public void setUserSignature(String userSignature) {
		this.userSignature = userSignature;
	}
	public String getUserPic() {
		return userPic;
	}
	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}
	public int getUserDegree() {
		return userDegree;
	}
	public void setUserDegree(int userDegree) {
		this.userDegree = userDegree;
	}
	
}
