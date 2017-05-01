package com.just.dao;

import java.util.List;

import com.just.model.Show;
import com.just.model.ShowComment;

public interface IShows {
	//==============================web端============================================
	//web端分页查询所有朋友圈
	List<Show>  getShowsBypage(int currentpageno,int pageSize);
	//删除show(与android端共用)
	int deleteShows(String showId);
	//=================================android端=============================================
	//添加show
	int addShow(Show show);
	//获取所有show
	List<Show> getAllShow();
	//获取my show
	List<Show> getMyShow(String userId);
	//给show点赞（更新show的点赞）
	int updateShowPraise(String showId);
}
