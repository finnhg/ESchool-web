package com.just.json;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.just.dao.impl.UserAdviceDaoImpl;
import com.just.model.UserAdvice;

/**
 * Servlet implementation class UserAdviceServletJson
 */
@WebServlet("/UserAdviceServletJson")
public class UserAdviceServletJson extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserAdviceServletJson() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		if (request.getParameter("type")!=null) {
			//添加反馈意见
			if (request.getParameter("type").equals("submitAdvice")) {
				//user_id,user_name,advice_keyword,user_advice
				String userId=request.getParameter("user_id");
				String userName=new String(request.getParameter("user_name").getBytes("ISO-8859-1"),"utf-8");
				String adviceKeyword=new String(request.getParameter("advice_keyword").getBytes("ISO-8859-1"),"utf-8");
				String userAdviceMessage=new String(request.getParameter("user_advice").getBytes("ISO-8859-1"),"utf-8"); 
				UserAdvice userAdvice=new UserAdvice(userId, userName, adviceKeyword, userAdviceMessage);
				int i=new UserAdviceDaoImpl().addUserAdvice(userAdvice);
				out.print(i);
			}
			
			
		}
	}

}
