package com.wangc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.wangc.dao.IUserAdvice;
import com.wangc.model.UserAdvice;
import com.wangc.util.ConnectionManager;

public class UserAdviceDaoImpl implements IUserAdvice{
	private static Connection conn;
	private static PreparedStatement psmt;
	private ResultSet rs;
	
	@Override
	public List<UserAdvice> getUserAdvicesByPage(int currentPageNo, int pageSize) {
		List<UserAdvice> list=new ArrayList<>();
		conn=ConnectionManager.getConnection();
		String sql="select * from user_advice limit ?,?";
		
		return list;
	}

	@Override
	public int deleteUserAdviceByAdviceId(String adviceId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
