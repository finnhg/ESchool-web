package com.wangc.dao;

import com.wangc.model.Manager;

public interface IManager {
	//����IdPwd����ȡManager(����Ա��¼)
	Manager getManagerByIdPwd(String managerId,String ManagerPwd);
	
	
}
