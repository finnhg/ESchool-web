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

import com.just.dao.impl.CollectionDaoImpl;
import com.just.dao.impl.GoodsDaoImpl;
import com.just.model.Collection;
import com.just.model.Goods;

/**
 * Servlet implementation class CollectionServletJson
 */
@WebServlet("/CollectionServletJson")
public class CollectionServletJson extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public CollectionServletJson() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		if (request.getParameter("type")!=null) {
			//添加收藏
			if (request.getParameter("type").equals("addCollection")) {
				String userId=request.getParameter("user_id");
				String goodsId=request.getParameter("goods_id");
				int i=new CollectionDaoImpl().addCollection(userId, goodsId);
				out.print(i);
			}
			//根据userId查询收藏
			if (request.getParameter("type").equals("getCollectionByUserId")) {
				String userId=request.getParameter("user_id");
				List<Collection> list=new CollectionDaoImpl().getCollectionsByUserId(userId);
				out.println(JSONArray.fromObject(list).toString());
			}
			//取消收藏
			if (request.getParameter("type").equals("cancelCollection")) {
				String userId=request.getParameter("user_id");
				String goodsId=request.getParameter("goods_id");
				int i=new CollectionDaoImpl().cancelCollection(userId, goodsId);
				out.print(i);
			}
			
		}
	}

}
