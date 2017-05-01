package com.just.dao;

import java.util.List;
import com.just.model.Collection;

/**
 * 收藏接口
 * @author kobe
 *
 */
public interface ICollection {
	//添加收藏
	int addCollection(String userId,String goodsId);
	//取消收藏
	int cancelCollection(String userId,String goodsId);
	//根据userId获取该用户的收藏
	List<Collection> getCollectionsByUserId(String userId);
}
