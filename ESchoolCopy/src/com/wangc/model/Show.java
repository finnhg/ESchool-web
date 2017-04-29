package com.wangc.model;
/**
 * 朋友圈类
 * @author wangc
 *
 */
public class Show {
	private String showId;//朋友圈Id
	private String showMessage;//朋友圈内容
	private String showImg;//朋友圈图片
	private String showTime;//发布朋友圈的时间
	private int showPraise;//朋友圈的点赞数量
	private String userId;//发布朋友圈的userId
	private String userName;//发布朋友圈的userName
	private int commentCount;//朋友圈的评论数量
	//无参构造函数
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


	//有参构造函数
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
