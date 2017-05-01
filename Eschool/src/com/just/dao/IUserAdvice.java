package com.just.dao;

import java.util.List;
import com.just.model.UserAdvice;

public interface IUserAdvice {
	//==================================web端===================================================
	//web端分页查询用户
	List<UserAdvice> getUserAdviceBypage(int currentpageno,int pageSize);
	//删除
	int deleteUserAdvice(String adviceId);
	
	//==================================android端===================================================
	//添加用户反馈意见
	int addUserAdvice(UserAdvice userAdvice);
}
