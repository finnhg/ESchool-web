package com.just.dao;

import java.util.List;
import com.just.model.Goods;
/**
 * Goods接口
 * @author kobe
 *
 */
public interface IGoods {
	//====================================================web端===========================================
	//删除商品
	int deleteGoods(String goodsId);
	//分页逻辑
	List<Goods> getGoodsBypage(int currentpageno,int pageSize);
	
	
	
	//====================================================android端===========================================
	
	//添加商品
	int addGoods(Goods goods);
	
	
	//更新商品状态
	int updateGoodsState(String userId,String goodsId);
	
	//根据用户id获取发布的商品
	List<Goods> getPublishGoodsByUserId(String userId);
	
	//搜索goods
	List<Goods> getSearchGoods(String keyWord);
	
	//根据用户id获取收藏的商品
	List<Goods> getCollectionGoodsByUserId(String userId);
	
	//获取所有商品
	List<Goods> getAllGoods();
	
	//获取goods State
	int getGoodsState(String goodsId);
}
