package com.wangc.dao;

import java.util.List;

import com.wangc.model.Show;

/**
 * ���е�����Ȧ
 * @author wangc
 *
 */
public interface IShows {
	//��ҳ��ȡShows
	List<Show> getShowsListBypageNo(int currentPageNo,int pageSize);
	//ɾ��Show
	int deleteShowByShowId(String showId);
}
