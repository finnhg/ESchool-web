package com.just.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.just.dao.impl.GoodsDaoImpl;
import com.just.dao.impl.UserAdviceDaoImpl;
import com.just.model.UserAdvice;

/**
 * Servlet implementation class UserAdviceServlet
 */
@WebServlet("/UserAdviceServlet")
public class UserAdviceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAdviceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		if (request.getParameter("type")!=null) {
			//分页显示
			if (request.getParameter("type").equals("pages")) {
				int pageSize=5;
				String pageno = request.getParameter("currentpageno");
				int currentpageno = 1;
				if (pageno != null) {
					currentpageno = Integer.parseInt(pageno);
				} else {
					currentpageno = 1;
				}
				List<UserAdvice> list=new UserAdviceDaoImpl().getUserAdviceBypage(currentpageno, pageSize);
				request.setAttribute("List", list);
				request.getRequestDispatcher("user_advice_list.jsp?currentpageno="+currentpageno+ "&pageCount=" + UserAdviceDaoImpl.getPageCount(pageSize)+"").forward(request, response);
			}
			//删除
			if (request.getParameter("type").equals("delete")) {
				String advice_id=request.getParameter("advice_id");
				int i=new UserAdviceDaoImpl().deleteUserAdvice(advice_id);
				if(i>0){
					out.print("<script>alert('删除成功！');window.location.href='UserAdviceServlet?type=pages&currentpageno=1'</script>");
				}else {
					out.print("<script>alert('删除失败！');");
				}
			}
		}
	}

}
