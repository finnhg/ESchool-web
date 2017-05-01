package com.just.model;


/**
 * 用户反馈意见类
 * @author kobe
 *
 */
public class UserAdvice {
	private String adviceId;//意见id
	private String userId;//用户id
	private String userName;//用户姓名
	private String adviceKeyword;//意见关键字
	private String userAdvice;//意见内容
	private String adviceTime;//提交意见时间
	public UserAdvice(String userId, String userName, String adviceKeyword,
			String userAdvice) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.adviceKeyword = adviceKeyword;
		this.userAdvice = userAdvice;
	}
	public UserAdvice(String adviceId, String userId, String userName,
			String adviceKeyword, String userAdvice, String adviceTime) {
		super();
		this.adviceId = adviceId;
		this.userId = userId;
		this.userName = userName;
		this.adviceKeyword = adviceKeyword;
		this.userAdvice = userAdvice;
		this.adviceTime = adviceTime;
	}
	public String getAdviceId() {
		return adviceId;
	}
	public void setAdviceId(String adviceId) {
		this.adviceId = adviceId;
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
	public String getAdviceKeyword() {
		return adviceKeyword;
	}
	public void setAdviceKeyword(String adviceKeyword) {
		this.adviceKeyword = adviceKeyword;
	}
	public String getUserAdvice() {
		return userAdvice;
	}
	public void setUserAdvice(String userAdvice) {
		this.userAdvice = userAdvice;
	}
	public String getAdviceTime() {
		return adviceTime;
	}
	public void setAdviceTime(String adviceTime) {
		this.adviceTime = adviceTime;
	}
	
}
