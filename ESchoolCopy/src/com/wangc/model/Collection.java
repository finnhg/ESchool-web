package com.wangc.model;
/**
 * �ղ���
 * @author wangc
 *
 */
public class Collection {
	private String collectionId;//�ղ�Id
	private String goodsId;//��ƷId
	private String userId;//�û�Id
	private String goodsImg;//��ƷͼƬ
	private String goodsName;//��Ʒ����
	private String goodsInfo;//��Ʒ��Ϣ
	private double goodsPrice;//��Ʒ�۸�
	private String goodsTime;//��Ʒ����ʱ��
	
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
	private int goodsStatus;//��Ʒ״̬
	private String goodsUserName;//����������
	
}
