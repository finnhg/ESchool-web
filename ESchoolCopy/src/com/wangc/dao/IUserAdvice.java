package com.wangc.dao;

import java.util.List;

import com.wangc.model.UserAdvice;

public interface IUserAdvice {
	//����ҳ����ѯ�û��������
	List<UserAdvice> getUserAdvicesByPage(int currentPageNo,int pageSize);
	
	//ɾ���û��������
	int deleteUserAdviceByAdviceId(String adviceId);
	
}
