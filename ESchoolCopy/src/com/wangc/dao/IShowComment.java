package com.wangc.dao;

import java.util.List;

import com.wangc.model.ShowComment;

public interface IShowComment {
	//根据showId获取ShowComment
	List<ShowComment> getShowCommentByShowId(int currentPageNo,int pageSize,String showId );
	//删除朋友圈评论
	int deleteShowComment(String showCommentId);
}
