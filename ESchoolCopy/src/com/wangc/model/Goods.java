package com.wangc.model;
/**
 * ��Ʒ��
 * @author wangc
 *
 */
public class Goods {
	private String goodsId;//��ƷId
	private String goodsName;//��Ʒ����
	private String goodsInfo;//��Ʒ��Ϣ
	private double goodsPrice;//��Ʒ�۸�
	private String goodsPic;//��ƷͼƬ
	private String goodsTime;//����ʱ��
	private int goodsState;//��Ʒ״̬
	private String userId;//������Ʒ��userId
	private String userName;//������Ʒ��userName
	
	//�޲ι��췽��
	public Goods() {
		super();
	}
	//�вι��췽��
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
