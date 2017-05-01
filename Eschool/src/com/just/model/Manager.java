package com.just.model;
/**
 * 管理员类
 * @author kobe
 *
 */
public class Manager {
	private String managerId;//id
	private String managerName;//姓名
	private String managerPwd;//密码
	private String managerGender;//性别
	private String managerPhone;//电话
	private String managerPic;//头像

	
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
