package com.wangc.model;
/**
 * ����Ա��
 * @author wangc
 *
 */
public class Manager {
	private String managerId;//����ԱID
	private String managerName;//����Ա����
	private String managerPwd;//����Ա����
	private String managerGender;//����Ա�Ա�
	private String managerPhone;//����Ա�绰
	private String managerPic;//����Աͷ��
	//�޲ι��캯��
	public Manager() {
		super();
	}
	//�вι��캯��
	public Manager(String managerId, String managerName, String managerPwd,
			String managerGender, String managerPhone, String managerPic) {
		super();
		this.managerId = managerId;
		this.managerName = managerName;
		this.managerPwd = managerPwd;
		this.managerGender = managerGender;
		this.managerPhone = managerPhone;
		this.managerPic = managerPic;
		
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerPwd() {
		return managerPwd;
	}
	public void setManagerPwd(String managerPwd) {
		this.managerPwd = managerPwd;
	}
	public String getManagerGender() {
		return managerGender;
	}
	public void setManagerGender(String managerGender) {
		this.managerGender = managerGender;
	}
	public String getManagerPhone() {
		return managerPhone;
	}
	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}
	public String getManagerPic() {
		return managerPic;
	}
	public void setManagerPic(String managerPic) {
		this.managerPic = managerPic;
	}
	

}
