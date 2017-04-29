package com.wangc.model;

import java.sql.Date;

/**
 * �û���
 * @author wangc
 *
 */
public class User {
	private String userId;//userId
	private String userName;//user����
	private String userPwd;//user����
	private String userGender;//user�Ա�
	private String userPhone;//user�绰
	private String userSchool;//userѧУ
	private String userBirthdate;//user��������
	private String userSignature;//user����ǩ��
	private String userPic;//�û�ͷ��
	private int userDegree;//user���õȼ�
	private int goodsCount;//user��������Ʒ����
	private int showsCount;//user������Ȧ����
	private int collectionsCount;//user���ղ�����
	//�޲ι��췽��
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



	//�вι��췽��
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
