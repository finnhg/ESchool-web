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
import com.just.model.ShowComment;

/**
 * Servlet implementation class ShowCommentServlet
 */
@WebServlet("/ShowCommentServlet")
public class ShowCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShowCommentServlet() {
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
			//查詢某條朋友圈的所有評論allcomments
			if (request.getParameter("type").equals("allcomments")) {
				String show_id=request.getParameter("show_id");
				String pageno=request.getParameter("currentpageno");
				int pageSize=5;
				int currentpageno=1;
				if (pageno!=null) {
					currentpageno=Integer.parseInt(pageno);
				}else {
					currentpageno=1;
				}
				List<ShowComment> list=new ShowCommentDaoImpl().getShowCommentByShowId(currentpageno, pageSize, show_id);
				request.setAttribute("List", list);
				request.getRequestDispatcher("one_show_comment.jsp?currentpageno="+currentpageno+ "&show_id="+show_id+"&pageCount=" + ShowCommentDaoImpl.getShowCommentPageCount(pageSize, show_id)+"").forward(request, response);
			}
			//删除朋友圈的某条评论
			if (request.getParameter("type").equals("deletecomment")) {
				String show_comment_id=request.getParameter("show_comment_id");
				String show_id=ShowCommentDaoImpl.getShowIdByShowCommentId(show_comment_id);
				int i=new ShowCommentDaoImpl().deleteShowComment(show_comment_id);
				if (i>0) {
					out.print("<script>alert('删除成功！');window.location.href='ShowCommentServlet?type=allcomments&show_id="+show_id+"&currentpageno=1'</script>");
				}else {
					out.print("<script>alert('删除失败！');");
				}
			}
			
			
		}
	}

}
