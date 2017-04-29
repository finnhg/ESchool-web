package com.wangc.model;
/**
 * ����Ȧ��
 * @author wangc
 *
 */
public class Show {
	private String showId;//����ȦId
	private String showMessage;//����Ȧ����
	private String showImg;//����ȦͼƬ
	private String showTime;//��������Ȧ��ʱ��
	private int showPraise;//����Ȧ�ĵ�������
	private String userId;//��������Ȧ��userId
	private String userName;//��������Ȧ��userName
	private int commentCount;//����Ȧ����������
	//�޲ι��캯��
	public Show() {
		super();
	}
	
	public Show(String showId, String showMessage, String showImg,
			String showTime, int showPraise, String userId, String userName) {
		super();
		this.showId = showId;
		this.showMessage = showMessage;
		this.showImg = showImg;
		this.showTime = showTime;
		this.showPraise = showPraise;
		this.userId = userId;
		this.userName = userName;
	}


	//�вι��캯��
	public Show(String showId, String showMessage, String showImg,
			String showTime, int showPraise, String userId, String userName,
			int commentCount) {
		super();
		this.showId = showId;
		this.showMessage = showMessage;
		this.showImg = showImg;
		this.showTime = showTime;
		this.showPraise = showPraise;
		this.userId = userId;
		this.userName = userName;
		this.commentCount = commentCount;
	}
	public String getShowId() {
		return showId;
	}
	public void setShowId(String showId) {
		this.showId = showId;
	}
	public String getShowMessage() {
		return showMessage;
	}
	public void setShowMessage(String showMessage) {
		this.showMessage = showMessage;
	}
	public String getShowImg() {
		return showImg;
	}
	public void setShowImg(String showImg) {
		this.showImg = showImg;
	}
	public String getShowTime() {
		return showTime;
	}
	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}
	public int getShowPraise() {
		return showPraise;
	}
	public void setShowPraise(int showPraise) {
		this.showPraise = showPraise;
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
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

}
