package com.just.model;
/**
 * 用户搜藏的商品
 * @author kobe
 *
 */
public class Collection {
	private String collectionId;//收藏id
	private String goodsId;//商品id
	private String userId;//用戶id
	private String goodsImg;//图片
	private String goodsName;//商品名称
	private String goodsInfo;//商品信息
	private double goodsPrice;//商品价格
	private String goodsTime;//发布时间
	private int goodsState;//商品状态
	private String goodsUserName;//发布人姓名

	public Collection(String collectionId, String goodsId, String userId,
			String goodsImg, String goodsName, String goodsInfo,
			double goodsPrice, String goodsTime, int goodsState,
			String goodsUserName) {
		super();
		this.collectionId = collectionId;
		this.goodsId = goodsId;
		this.userId = userId;
		this.goodsImg = goodsImg;
		this.goodsName = goodsName;
		this.goodsInfo = goodsInfo;
		this.goodsPrice = goodsPrice;
		this.goodsTime = goodsTime;
		this.goodsState = goodsState;
		this.goodsUserName = goodsUserName;
	}

	
	public String getGoodsImg() {
		return goodsImg;
	}
	public void setGoodsImg(String goodsImg) {
		this.goodsImg = goodsImg;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsInfo() {
		return goodsInfo;
	}
	public void setGoodsInfo(String goodsInfo) {
		this.goodsInfo = goodsInfo;
	}
	public double getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public String getGoodsTime() {
		return goodsTime;
	}
	public void setGoodsTime(String goodsTime) {
		this.goodsTime = goodsTime;
	}
	public int getGoodsState() {
		return goodsState;
	}
	public void setGoodsState(int goodsState) {
		this.goodsState = goodsState;
	}
	public String getGoodsUserName() {
		return goodsUserName;
	}
	public void setGoodsUserName(String goodsUserName) {
		this.goodsUserName = goodsUserName;
	}
	public Collection(String collectionId, String goodsId, String userId) {
		super();
		this.collectionId = collectionId;
		this.goodsId = goodsId;
		this.userId = userId;
	}
	public String getCollectionId() {
		return collectionId;
	}
	public void setCollectionId(String collectionId) {
		this.collectionId = collectionId;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
