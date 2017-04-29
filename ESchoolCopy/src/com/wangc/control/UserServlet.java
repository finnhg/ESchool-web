package com.wangc.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wangc.dao.impl.UserDaoImpl;
import com.wangc.model.User;


@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		if (request.getParameter("type")!=null) {
			//分页查询所有用户
			if (request.getParameter("type").equals("pages")) {
				int pageSize=3;
				String pageno=request.getParameter("currentpageno");
				int currrentpageno=1;
				if (pageno!=null) {
					currrentpageno=Integer.parseInt(pageno);
				}else {
					currrentpageno=1;
				}
				List<User> list=new UserDaoImpl().getUsersByPage(currrentpageno, pageSize);
				request.setAttribute("List", list);
				request.getRequestDispatcher("user_list.jsp?currentpageno="+currrentpageno+"&pageCount="+UserDaoImpl.getPageCount(pageSize)+"").forward(request, response);
			}
		}
		
	}

}
