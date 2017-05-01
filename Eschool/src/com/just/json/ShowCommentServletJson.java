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
import com.just.model.ShowComment;

/**
 * Servlet implementation class ShowCommentServletJson
 */
@WebServlet("/ShowCommentServletJson")
public class ShowCommentServletJson extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowCommentServletJson() {
		super();
	}

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	
	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		if (request.getParameter("type") != null) {
			//获取所有评论
			if (request.getParameter("type").equals("getAllComment")) {
				String showId=request.getParameter("show_id");
				System.out.println(showId);
				List<ShowComment> list=new ShowCommentDaoImpl().getShowComments(showId);
				System.out.println(list.size());
				out.print(JSONArray.fromObject(list).toString());
			}
			//添加评论
			if (request.getParameter("type").equals("addShowComment")) {
				// showId,userId,commenterId,commenter_name,commenter_img,show_comment_message
				String showId=request.getParameter("show_id");//showId
				String userId=request.getParameter("user_id");//发布朋友圈的用户id
				String commenterId=request.getParameter("commenter_id");//评论者id
				String commenterName=new String(request.getParameter("commenter_name").getBytes("ISO-8859-1"),"utf-8");//评论者姓名
				String commenterImg=request.getParameter("commenter_img");//评论者头像
				String showCommentMessage=new String(request.getParameter("show_comment_message").getBytes("ISO-8859-1"),"utf-8");//评论内容
				ShowComment showComment=new ShowComment(showId, userId, commenterId, commenterName, commenterImg, showCommentMessage);
				int i=new ShowCommentDaoImpl().addShowComment(showComment);
				out.print(i);
			}
			
			//获取评论的数量
			if (request.getParameter("type").equals("getShowCommentCount")) {
				String showId=request.getParameter("show_id");
				int i=new ShowCommentDaoImpl().getCommentCount(showId);
				out.print(i);
			}

		}
	}

}
