package com.wangc.control;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserAdviceServlet
 */
@WebServlet("/UserAdviceServlet")
public class UserAdviceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserAdviceServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		if (request.getParameter("type")!=null) {
			if (request.getParameter("type").equals("pages")) {
				
			}
		}
	}

}
