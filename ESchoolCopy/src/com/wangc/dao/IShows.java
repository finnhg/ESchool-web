package com.wangc.dao;

import java.util.List;

import com.wangc.model.Show;

/**
 * 所有的朋友圈
 * @author wangc
 *
 */
public interface IShows {
	//分页获取Shows
	List<Show> getShowsListBypageNo(int currentPageNo,int pageSize);
	//删除Show
	int deleteShowByShowId(String showId);
}
