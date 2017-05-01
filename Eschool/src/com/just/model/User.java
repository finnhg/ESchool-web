package com.just.model;

/**
 * 用户类
 * 
 * @author kobe
 *
 */
public class User {
	private String userId;// 用户Id
	private String userName;// 用户名
	private String userPassword;// 密码
	private String userGender;// 性别
	private String userPhone;// 电话
	private String userSchool;// 学校
	private String userBirthdate;// 生日
	private String userSignature;// 签名
	private int userDegree;//用户等级
	public User(String userId, String userName, String userGender,
			String userPhone, String userSchool, String userBirthdate,
			String userSignature, int userDegree, String userPic) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userGender = userGender;
		this.userPhone = userPhone;
		this.userSchool = userSchool;
		this.userBirthdate = userBirthdate;
		this.userSignature = userSignature;
		this.userDegree = userDegree;
		this.userPic = userPic;
	}

	private String userPic;//用户头像
	
	public User(String userId, String userName, String userPassword,
			String userGender, String userPhone, String userSchool,
			String userBirthdate, String userSignature, int userDegree,
			String userPic) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userGender = userGender;
		this.userPhone = userPhone;
		this.userSchool = userSchool;
		this.userBirthdate = userBirthdate;
		this.userSignature = userSignature;
		this.userDegree = userDegree;
		this.userPic = userPic;
	}
	
	//使用多个构造函数，根据不同的需要使用不同的构造函数
	private int goodsCount;//用户发布的商品数量
	private int showsCount;//用户的朋友圈数量
	private int collectionsCount;//用户收藏的商品数量
	
	public User(String userId, String userName, String userPassword,
			String userGender, String userPhone, String userSchool,
			String userBirthdate, String userSignature, int userDegree,
			String userPic, int goodsCount, int showsCount, int collectionsCount) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userGender = userGender;
		this.userPhone = userPhone;
		this.userSchool = userSchool;
		this.userBirthdate = userBirthdate;
		this.userSignature = userSignature;
		this.userDegree = userDegree;
		this.userPic = userPic;
		this.goodsCount = goodsCount;
		this.showsCount = showsCount;
		this.collectionsCount = collectionsCount;
	}
	public int getCollectionsCount() {
		return collectionsCount;
	}
	public void setCollectionsCount(int collectionsCount) {
		this.collectionsCount = collectionsCount;
	}
	public int getShowsCount() {
		return showsCount;
	}
	public void setShowsCount(int showsCount) {
		this.showsCount = showsCount;
	}
	public int getGoodsCount() {
		return goodsCount;
	}
	public void setGoodsCount(int goodsCount) {
		this.goodsCount = goodsCount;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
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
	public int getUserDegree() {
		return userDegree;
	}
	public void setUserDegree(int userDegree) {
		this.userDegree = userDegree;
	}
	public String getUserPic() {
		return userPic;
	}
	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}

}
