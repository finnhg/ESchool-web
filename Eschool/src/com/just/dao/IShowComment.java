package com.just.dao;

import java.util.List;

import com.just.model.ShowComment;

/**
 *  朋友圈评论
 * @author kobe
 *
 */
public interface IShowComment {
	//======================================web端==============================================
	//根据朋友圈id查询该朋友圈所有评论(分页)
	List<ShowComment> getShowCommentByShowId(int currentpageno,int pageSize,String showId);
	//删除
	int deleteShowComment(String showCommentId);
	
	//======================================android端===================================================
	//添加朋友圈评论
	int addShowComment(ShowComment showComment);
	//查询所有评论（不分页）
	List<ShowComment> getShowComments(String showId);
	//查询评论数量
	int getCommentCount(String showId);
}
