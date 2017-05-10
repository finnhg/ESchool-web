package com.wangc.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wangc.dao.impl.ShowsDaoImpl;
import com.wangc.model.Show;

/**
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		if (request.getParameter("type")!=null) {
			//∑÷“≥œ‘ æ≈Û”—»¶
			if (request.getParameter("type").equals("pages")) {
				int pageSize=3;
				String pageno=request.getParameter("currentPageNo");
				int currentPageNo=1;
				if (pageno!=null) {
					currentPageNo=Integer.parseInt(pageno);
				}else {
					currentPageNo=1;
				}
				List<Show> list=new ShowsDaoImpl().getShowsListBypageNo(currentPageNo, pageSize);
				request.setAttribute("List", list);
				request.getRequestDispatcher("user_show_list.jsp?currentPageNo="+currentPageNo+"&pageCount="+ShowsDaoImpl.getPageCount(pageSize)+"").forward(request, response);
			}
			//…æ≥˝≈Û”—»¶
			if (request.getParameter("type").equals("delete")) {
				String showId=request.getParameter("showId");
				int i=new ShowsDaoImpl().deleteShowByShowId(showId);
				if (i>0) {
					out.print("<script>alert('…æ≥˝≥…π¶£°');window.location.href='ShowsServlet?type=pages&currentPageNo=1'</script>");
				}else {
					out.print("<script>alert('…æ≥˝ ß∞‹£°')</script>");
				}
			}
		}
		
		
	}

}
