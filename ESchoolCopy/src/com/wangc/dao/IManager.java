package com.wangc.dao;

import com.wangc.model.Manager;

public interface IManager {
	//根据IdPwd来获取Manager(管理员登录)
	Manager getManagerByIdPwd(String managerId,String ManagerPwd);
	
	
}
