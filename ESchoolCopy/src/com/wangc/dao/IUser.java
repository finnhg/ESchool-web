package com.wangc.dao;

import java.util.List;

import com.wangc.model.Collection;
import com.wangc.model.Goods;
import com.wangc.model.Show;
import com.wangc.model.User;

public interface IUser {
	//Web端分页查询，根据页数获取user
	List<User> getUsersByPage(int currentPageNo,int pageSize);
	//根据userId获取所有商品
	List<Goods> getGoodsByUserId(int currentPageNo,int pageSize,String userId);
	//根据userId获取该user的所有朋友圈
	List<Show> getShowsByUserId(int currentPageNo,int pageSize,String userId);
	//根据userId获取该user的所有收藏
	List<Collection> getCollectionsByUserId(int currentpageNo,int pageSize,String userId);
}
