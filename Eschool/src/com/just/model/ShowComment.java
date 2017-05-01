package com.just.model;
/**
 * 朋友圈评论类
 * @author kobe
 *
 */
public class ShowComment {
	// showId朋友圈id,userId,commenterId,commenter_name,commenter_img,show_comment_message
	private String showCommentId;//评论id
	private String showId;//朋友圈id
	private String userId;//发朋友圈的用户的id
	private String commenterId;//评论者的id
	private String commenterName;//评论者姓名
	private String commenterImg;//评论者头像
	private String commentTime;//评论时间
	private String showCommentMessage;//评论的内容
	public ShowComment(String showId, String userId, String commenterId,
			String commenterName, String commenterImg, String showCommentMessage) {
		super();
		this.showId = showId;
		this.userId = userId;
		this.commenterId = commenterId;
		this.commenterName = commenterName;
		this.commenterImg = commenterImg;
		this.showCommentMessage = showCommentMessage;
	}
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
