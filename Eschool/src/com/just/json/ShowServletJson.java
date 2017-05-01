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

import com.just.dao.impl.ShowCommentDaoImpl;
import com.just.dao.impl.ShowDaoImpl;
import com.just.model.Show;

/**
 * Servlet implementation class ShowServletJson
 */
@WebServlet("/ShowServletJson")
public class ShowServletJson extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShowServletJson() {
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
			//添加show
			if (request.getParameter("type").equals("addShow")) {
				String showMessage=new String(request.getParameter("show_message").getBytes("ISO-8859-1"),"utf-8");
				String showImg=request.getParameter("show_img");
				String userId=request.getParameter("user_id");
				Show show=new Show(showMessage, showImg, userId);
				int i=new ShowDaoImpl().addShow(show);
				out.print(i);
			}
			//删除show
			if (request.getParameter("type").equals("deleteShow")) {
				String showId=request.getParameter("show_id");
				int i=new ShowDaoImpl().deleteShows(showId);
				out.print(i);
			}
			//获取所有show
			if (request.getParameter("type").equals("getAllShow")) {
				List<Show> list=new ShowDaoImpl().getAllShow();
				out.print(JSONArray.fromObject(list).toString());
			}
			//获取my show
			if (request.getParameter("type").equals("getMyShow")) {
				String userId=request.getParameter("user_id");
				List<Show> list=new ShowDaoImpl().getMyShow(userId);
				out.print(JSONArray.fromObject(list).toString());
			}
			//给show点赞（更新show的点赞数量）
			if (request.getParameter("type").equals("updateShowPraise")) {
				String showId=request.getParameter("show_id");
				int i=new ShowDaoImpl().updateShowPraise(showId);
				out.print(i);
			}
			
		}
	}

}
