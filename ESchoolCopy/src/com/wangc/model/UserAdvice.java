package com.wangc.model;

/**
 * �û����������
 * @author wangc
 *
 */
public class UserAdvice {
	private String adviceId;//���Id
	private String userId;//�û�Id
	private String userName;//�û�����
	private String adviceKeyword;//����ؼ���
	private String userAdvice;//�û����
	private String adviceTime;//����ύʱ��
	
	public UserAdvice() {
		super();
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
