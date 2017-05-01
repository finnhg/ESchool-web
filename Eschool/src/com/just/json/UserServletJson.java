package com.just.json;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.just.dao.impl.UserDaoImpl;
import com.just.model.User;

/**
 * Servlet implementation class UserServlet
 * 处理一切android端关于用户的请求
 */
@WebServlet("/UserServletJson")
public class UserServletJson extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public UserServletJson() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");// 设置处理请求参数的编码格式
		response.setContentType("text/html; charset=utf-8");// 设置Content-Type字段值
		PrintWriter out = response.getWriter();
		
		if (request.getParameter("type")!=null) {
			//验证用户是否已经存在
			if (request.getParameter("type").equals("checkUserExist")) {
				String userPhone=request.getParameter("user_phone");
				int i=new UserDaoImpl().checkUserExist(userPhone);
				out.print(i);
			}
			//注册用户
			if (request.getParameter("type").equals("register")) {
				String userPhone=request.getParameter("user_phone");
				String userPwd=request.getParameter("user_pwd");
				int i=new UserDaoImpl().register(userPhone, userPwd);
				out.print(i);
			}
			//获取注册成功后数据库生成的userId
			if (request.getParameter("type").equals("getUserIdByUserPhone")) {
				String userPhone=request.getParameter("user_phone");
				String userId=new UserDaoImpl().getUserIdByUserPhone(userPhone);
				out.print(userId);
			}
			//登录(电话，密码)
			if (request.getParameter("type").equals("login")) {
				String user_phone=request.getParameter("user_phone");
				String user_password=request.getParameter("user_password");
				List<User> list=new UserDaoImpl().getUserByPhonePwd(user_phone, user_password);
				if (list.size()==1) {
					out.print(JSONArray.fromObject(list).toString());
				}else {
					out.print(0);
				}
			}
			//登录(短信验证登录)
			if (request.getParameter("type").equals("loginByPhone")) {
				String user_phone=request.getParameter("user_phone");
				List<User> list=new UserDaoImpl().getUserByPhone(user_phone);
				if (list.size()==1) {
					out.print(JSONArray.fromObject(list).toString());
				}else {
					out.print(0);
				}
			}
			//修改用户名
			if (request.getParameter("type").equals("updateName")) {
				String userId=request.getParameter("user_id");
				String userName=new String(request.getParameter("user_name").getBytes("ISO-8859-1"),"utf-8");
				int i=new UserDaoImpl().updateUserName(userId, userName);
				out.print(i);
			}
			//修改用户签名
			if (request.getParameter("type").equals("updateSignature")) {
				String userId=request.getParameter("user_id");
				String userSignature=new String(request.getParameter("user_signature").getBytes("ISO-8859-1"),"utf-8");
				int i=new UserDaoImpl().updateUserSignature(userId, userSignature);
				out.print(i);			
			}
			//修改性别
			if (request.getParameter("type").equals("updateGender")) {
				String userId=request.getParameter("user_id");
				String userGender=new String(request.getParameter("user_gender").getBytes("ISO-8859-1"),"utf-8");
				int i=new UserDaoImpl().updateUserGender(userId, userGender);
				out.print(i);
			}
			//修改出生日期
			if (request.getParameter("type").equals("updateBirthdate")) {
				String userId=request.getParameter("user_id");
				String userBirthdate=request.getParameter("user_birthdate");
				int i=new UserDaoImpl().updateUserBirthdate(userId, userBirthdate);
				out.print(i);
			}
			//修改学校
			if (request.getParameter("type").equals("updateSchool")) {
				String userId=request.getParameter("user_id");
				String userSchool=new String(request.getParameter("user_school").getBytes("ISO-8859-1"),"utf-8");
				int i=new UserDaoImpl().updateUserSchool(userId, userSchool);
				out.print(i);
			}
			//根据userId获取userPic
			if (request.getParameter("type").equals("getUserPicByUserId")) {
				String userId=request.getParameter("user_id");
				String userPic=new UserDaoImpl().getUserPicByUserId(userId);
				out.print(userPic);
			}
			//根据userId获取userDegree
			if (request.getParameter("type").equals("getUserDegreeByUserId")) {
				String userId=request.getParameter("user_id");
				int userDegree=new UserDaoImpl().getUserDegreeByUserId(userId);
				out.print(userDegree);
			}
			//更具userId获取User
			if (request.getParameter("type").equals("getUserByUserId")) {
				String userId=request.getParameter("user_id");
				User user=new UserDaoImpl().getUserByUserId(userId);
				out.print(JSONArray.fromObject(user).toString());
			}
			

		}
		
		
	
		
	}

}
