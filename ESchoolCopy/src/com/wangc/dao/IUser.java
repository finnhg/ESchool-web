package com.wangc.dao;

import java.util.List;

import com.wangc.model.Collection;
import com.wangc.model.Goods;
import com.wangc.model.Show;
import com.wangc.model.User;

public interface IUser {
	//Web�˷�ҳ��ѯ������ҳ����ȡuser
	List<User> getUsersByPage(int currentPageNo,int pageSize);
	//����userId��ȡ������Ʒ
	List<Goods> getGoodsByUserId(int currentPageNo,int pageSize,String userId);
	//����userId��ȡ��user����������Ȧ
	List<Show> getShowsByUserId(int currentPageNo,int pageSize,String userId);
	//����userId��ȡ��user�������ղ�
	List<Collection> getCollectionsByUserId(int currentpageNo,int pageSize,String userId);
}
