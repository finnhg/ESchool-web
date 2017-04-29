package com.wangc.model;
/**
 * 朋友圈评论类
 * @author wangc
 *
 */
public class ShowComment {
	private String showCommentId;
	private String showId;
	private String userId;
	private String commenterId;
	private String commenterName;
	private String commenterImg;
	private String commentTime;
	private String showCommentMessage;
	//无参构造函数
	public ShowComment() {
		super();
	}
	//有参构造函数
	public ShowComment(String showCommentId, String showId, String userId,
			String commenterId, String commenterName, String commenterImg,
			String commentTime, String showCommentMessage) {
		super();
		this.showCommentId = showCommentId;
		this.showId = showId;
		this.userId = userId;
		this.commenterId = commenterId;
		this.commenterName = commenterName;
		this.commenterImg = commenterImg;
		this.commentTime = commentTime;
		this.showCommentMessage = showCommentMessage;
	}
	public String getShowCommentId() {
		return showCommentId;
	}
	public void setShowCommentId(String showCommentId) {
		this.showCommentId = showCommentId;
	}
	public String getShowId() {
		return showId;
	}
	public void setShowId(String showId) {
		this.showId = showId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCommenterId() {
		return commenterId;
	}
	public void setCommenterId(String commenterId) {
		this.commenterId = commenterId;
	}
	public String getCommenterName() {
		return commenterName;
	}
	public void setCommenterName(String commenterName) {
		this.commenterName = commenterName;
	}
	public String getCommenterImg() {
		return commenterImg;
	}
	public void setCommenterImg(String commenterImg) {
		this.commenterImg = commenterImg;
	}
	public String getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime;
	}
	public String getShowCommentMessage() {
		return showCommentMessage;
	}
	public void setShowCommentMessage(String showCommentMessage) {
		this.showCommentMessage = showCommentMessage;
	}
	
	
}
