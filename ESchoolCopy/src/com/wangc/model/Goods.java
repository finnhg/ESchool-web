package com.wangc.model;
/**
 * 商品类
 * @author wangc
 *
 */
public class Goods {
	private String goodsId;//商品Id
	private String goodsName;//商品名称
	private String goodsInfo;//商品信息
	private double goodsPrice;//商品价格
	private String goodsPic;//商品图片
	private String goodsTime;//发布时间
	private int goodsState;//商品状态
	private String userId;//发布商品的userId
	private String userName;//发布商品的userName
	
	//无参构造方法
	public Goods() {
		super();
	}
	//有参构造方法
	public Goods(String goodsId, String goodsName, String goodsInfo,
			double goodsPrice, String goodsPic, String goodsTime,
			int goodsState, String userId, String userName) {
		super();
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.goodsInfo = goodsInfo;
		this.goodsPrice = goodsPrice;
		this.goodsPic = goodsPic;
		this.goodsTime = goodsTime;
		this.goodsState = goodsState;
		this.userId = userId;
		this.userName = userName;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
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
	public String getGoodsPic() {
		return goodsPic;
	}
	public void setGoodsPic(String goodsPic) {
		this.goodsPic = goodsPic;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

}
