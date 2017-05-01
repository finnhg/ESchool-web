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
import com.just.dao.impl.ShowCommentDaoImpl;
import com.just.dao.impl.ShowDaoImpl;
import com.just.model.Show;
import com.just.model.ShowComment;

/**朋友圈Servlet
 * Servlet implementation class ShowsServlet
 */
@WebServlet("/ShowsServlet")
public class ShowsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ShowsServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		if (request.getParameter("type")!=null) {
			//分页显示所有朋友圈
			if (request.getParameter("type").equals("pages")) {
				int pageSize=3;
				String pageno = request.getParameter("currentpageno");
				int currentpageno = 1;
				if (pageno != null) {
					currentpageno = Integer.parseInt(pageno);
				} else {
					currentpageno = 1;
				}
				List<Show> list=new ShowDaoImpl().getShowsBypage(currentpageno, pageSize);
				request.setAttribute("List", list);
				request.getRequestDispatcher("user_show_list.jsp?currentpageno="+currentpageno+ "&pageCount=" + ShowDaoImpl.getPageCount(pageSize)+"").forward(request, response);
			}
			
			
			//删除朋友圈
			if (request.getParameter("type").equals("delete")) {
				String show_id=request.getParameter("show_id");
				int i=new ShowDaoImpl().deleteShows(show_id);
				if(i>0){
					out.print("<script>alert('删除成功！');window.location.href='ShowsServlet?type=pages&currentpageno=1'</script>");
				}else {
					out.print("<script>alert('删除失败！');");
				}
			}
		}
	}

}
