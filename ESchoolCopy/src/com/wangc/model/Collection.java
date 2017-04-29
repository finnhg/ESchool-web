package com.wangc.model;
/**
 * 收藏类
 * @author wangc
 *
 */
public class Collection {
	private String collectionId;//收藏Id
	private String goodsId;//商品Id
	private String userId;//用户Id
	private String goodsImg;//商品图片
	private String goodsName;//商品名称
	private String goodsInfo;//商品信息
	private double goodsPrice;//商品价格
	private String goodsTime;//商品发布时间
	
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
	public int getGoodsStatus() {
		return goodsStatus;
	}
	public void setGoodsStatus(int goodsStatus) {
		this.goodsStatus = goodsStatus;
	}
	public String getGoodsUserName() {
		return goodsUserName;
	}
	public void setGoodsUserName(String goodsUserName) {
		this.goodsUserName = goodsUserName;
	}
	public Collection() {
		super();
	}
	public Collection(String collectionId, String goodsId, String userId,
			String goodsImg, String goodsName, String goodsInfo,
			double goodsPrice, String goodsTime, int goodsStatus,
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
		this.goodsStatus = goodsStatus;
		this.goodsUserName = goodsUserName;
	}
	private int goodsStatus;//商品状态
	private String goodsUserName;//发布人姓名
	
}
