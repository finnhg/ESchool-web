package com.wangc.dao;

import java.util.List;

import com.wangc.model.ShowComment;

public interface IShowComment {
	//����showId��ȡShowComment
	List<ShowComment> getShowCommentByShowId(int currentPageNo,int pageSize,String showId );
	//ɾ������Ȧ����
	int deleteShowComment(String showCommentId);
}
