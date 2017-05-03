package com.wangc.control;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wangc.dao.impl.UserAdviceDaoImpl;
import com.wangc.dao.impl.UserDaoImpl;
import com.wangc.model.UserAdvice;

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
		//如果获取的参数不为null
		if (request.getParameter("type")!=null) {
			//如果获取的参数是要分页显示user_advice
			if (request.getParameter("type").equals("pages")) {
				int pageSize=5;
				String currentPageNoString=request.getParameter("currentPageNo");
				int currentPageNo=1;
				if (currentPageNoString!=null) {
					currentPageNo=Integer.parseInt(currentPageNoString);
				}else {
					currentPageNo=1;
				}
				List<UserAdvice> list=new UserAdviceDaoImpl().getUserAdvicesByPage(currentPageNo, pageSize);
				request.setAttribute("List", list);
				request.getRequestDispatcher("user_advice_list.jsp?currentPageNo="+currentPageNo+"&pageCount="+UserAdviceDaoImpl.getPageCount(pageSize)).forward(request, response);
			}
			// 删除user_advice
			if (request.getParameter("type").equals("delete")) {
				String adviceId = request.getParameter("advice_id");
				int i = new UserAdviceDaoImpl().deleteUserAdviceByAdviceId(adviceId);
				if (i>0) {
					out.print("<script>alert('删除成功！');window.location.href='UserAdviceServlet?type=pages&currentPageNo=1'</script>");
				}else {
					out.print("<script>alert('删除失败')</script>");
				}
			}
		}
	}

}
