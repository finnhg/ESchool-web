package com.just.dao;

import java.util.List;

import com.just.model.Manager;

public interface IManager {
	//添加管理员
	int addManager(Manager manager);
	//删除管理员
	int deleteManager(String managerId);
	//根据IdPwd查询管理员
	Manager getManagerByIdPwd(String managerId,String managerPwd);
	//根据Id查询管理员
	Manager getManagerById(String managerId);
	//分页逻辑
	List<Manager> getManagerBypage(int currentpageno,int pageSize);
	//更改管理员信息
	int updateManager(Manager manager);
}
