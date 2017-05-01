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
import com.just.dao.impl.ShowDaoImpl;
import com.just.dao.impl.UserDaoImpl;
import com.just.model.Collection;
import com.just.model.Goods;
import com.just.model.Show;
import com.just.model.User;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserServlet() {
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
			//分页查询所有用户
			if (request.getParameter("type").equals("pages")) {
				int pageSize=3;
				String pageno = request.getParameter("currentpageno");
				int currentpageno = 1;
				if (pageno != null) {
					currentpageno = Integer.parseInt(pageno);
				} else {
					currentpageno = 1;
				}
				List<User> list=new UserDaoImpl().getUsersBypage(currentpageno, pageSize);
				request.setAttribute("List", list);
				request.getRequestDispatcher("user_list.jsp?currentpageno="+currentpageno+ "&pageCount=" + UserDaoImpl.getPageCount(pageSize)+"").forward(request, response);
			}
			//根据userId查询该用户的发布的商品
			if (request.getParameter("type").equals("showgoods")) {
				String user_id=request.getParameter("user_id");
				int pageSize=3;
				String pageno = request.getParameter("currentpageno");
				int currentpageno = 1;
				if (pageno != null) {
					currentpageno = Integer.parseInt(pageno);
				} else {
					currentpageno = 1;
				}
				List<Goods> list=new UserDaoImpl().getGoodsByUserId(currentpageno, pageSize, user_id);
				request.setAttribute("List", list);
				request.getRequestDispatcher("one_goods_list.jsp?currentpageno="+currentpageno+"&pageCount="+UserDaoImpl.getGoodsPageCountByUserId(pageSize, user_id)+"&user_name="+UserDaoImpl.getUserNameByUserId(user_id)+"&user_id="+user_id+"").forward(request, response);
			}
			//根据userId查询该用户的所有朋友圈
			if (request.getParameter("type").equals("showshows")) {
				String user_id=request.getParameter("user_id");
				String pageno=request.getParameter("currentpageno");
				int pageSize=3;
				int currentpageno=1;
				if (pageno!=null) {
					currentpageno=Integer.parseInt(pageno);
				}else {
					currentpageno=1;
				}
				List<Show> list=new UserDaoImpl().getShowByUserId(currentpageno, pageSize, user_id);
				request.setAttribute("List", list);
				request.getRequestDispatcher("one_show_list.jsp?currentpageno="+currentpageno+"&pageCount="+UserDaoImpl.getShowsPageCountByUserId(pageSize, user_id)+"&user_name="+UserDaoImpl.getUserNameByUserId(user_id)+"&user_id="+user_id+"").forward(request, response);
			}
			//根据userId查询该用户所有收藏的商品
			if (request.getParameter("type").equals("showcollections")) {
				String user_id=request.getParameter("user_id");
				int pageSize=3;
				String pageno=request.getParameter("currentpageno");
				int currentpageno=1;
				if (pageno!=null) {
					currentpageno=Integer.parseInt(pageno);
				}else {
					currentpageno=1;
				}
				List<Collection> list=new UserDaoImpl().getCollectionsByUserId(currentpageno, pageSize, user_id);
				request.setAttribute("List", list);
				request.getRequestDispatcher("one_collection_list.jsp?currentpageno="+currentpageno+"&pageCount="+UserDaoImpl.getCollectionPageCountByUserId(pageSize, user_id)+"&user_name="+UserDaoImpl.getUserNameByUserId(user_id)+"&user_id="+user_id+"").forward(request, response);
				
			}
			//删除某个人的某件商品
			if (request.getParameter("type").equals("deletegoods")) {
				String goods_id=request.getParameter("goods_id");
				String user_id=GoodsDaoImpl.getUserIdByGoodsId(goods_id);
				int i=new GoodsDaoImpl().deleteGoods(goods_id);
				if(i>0){
					out.print("<script>alert('删除成功！');window.location.href='UserServlet?type=showgoods&user_id="+user_id+"&currentpageno=1'</script>");
				}else {
					out.print("<script>alert('删除失败！');");
				}
			}
			
			//删除某个人的某条朋友圈
			if (request.getParameter("type").equals("deleteshow")) {
				String show_id=request.getParameter("show_id");
				String user_id=ShowDaoImpl.getUserIdByShowId(show_id);
				int i=new ShowDaoImpl().deleteShows(show_id);
				if (i>0) {
					out.print("<script>alert('删除成功！');window.location.href='UserServlet?type=showshows&user_id="+user_id+"&currentpageno=1'</script>");
				}else {
					out.print("<script>alert('删除失败！');");
				}
			}
		}
	}

}
