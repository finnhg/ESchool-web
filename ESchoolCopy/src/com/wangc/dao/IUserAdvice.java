package com.wangc.dao;

import java.util.List;

import com.wangc.model.UserAdvice;

public interface IUserAdvice {
	//根据页数查询用户反馈意见
	List<UserAdvice> getUserAdvicesByPage(int currentPageNo,int pageSize);
	
	//删除用户反馈意见
	int deleteUserAdviceByAdviceId(String adviceId);
	
}
