package com.wangc.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wangc.dao.impl.ManagerDaoImpl;
import com.wangc.model.Manager;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }
    
    

	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out=response.getWriter();
		HttpSession session=null;
		String managerId=request.getParameter("manager_id");
		String managerPwd=request.getParameter("manager_pwd");
		Manager manager=new ManagerDaoImpl().getManagerByIdPwd(managerId, managerPwd);
		if (manager!=null) {
			session=request.getSession();
			session.setAttribute("Manager", manager);
			RequestDispatcher dispatcher=request.getRequestDispatcher("main.jsp");
			dispatcher.forward(request, response);
		}else {
			out.print("<script>alert('用户名或密码错误');window.location.href='index.html';</script>");
		}
		
	}

}
