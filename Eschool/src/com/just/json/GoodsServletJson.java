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
import com.just.dao.impl.GoodsDaoImpl;
import com.just.model.Goods;

/**
 * Servlet implementation class GoodsServletJson
 */
@WebServlet("/GoodsServletJson")
public class GoodsServletJson extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public GoodsServletJson() {
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
			//添加商品
			if (request.getParameter("type").equals("addgoods")) {
				String goodsName=new String(request.getParameter("goods_name").getBytes("ISO-8859-1"),"utf-8");
				String goodsInfo=new String(request.getParameter("goods_info").getBytes("ISO-8859-1"),"utf-8");
				double goodsPrice=Double.parseDouble(request.getParameter("goods_price"));
				String goodsPic=request.getParameter("goods_pic");//商品价格
				String userId=request.getParameter("user_id");
				String userName=new String(request.getParameter("user_name").getBytes("ISO-8859-1"),"utf-8");
				Goods goods=new Goods(goodsName,goodsInfo,goodsPrice,goodsPic,userId,userName);
				int i=new GoodsDaoImpl().addGoods(goods);
				out.print(i);
			}
			//删除goods
			if (request.getParameter("type").equals("deleteGoods")) {
				String goodsId=request.getParameter("goods_id");
				int i=new GoodsDaoImpl().deleteGoods(goodsId);
				out.print(i);
			}
			//获取所有的goods
			if (request.getParameter("type").equals("getAllGoods")) {
				List<Goods> list=new GoodsDaoImpl().getAllGoods();
				System.out.println("所有goods："+list.size());
				out.println(JSONArray.fromObject(list).toString());
			}
			//搜索goods
			if (request.getParameter("type").equals("getSearchGoods")) {
				String keyWord=new String(request.getParameter("search_keyword").getBytes("ISO-8859-1"),"utf-8");
				List<Goods> list=new GoodsDaoImpl().getSearchGoods(keyWord);
				out.println(JSONArray.fromObject(list).toString());
			}
			//获取收藏的goods
			if (request.getParameter("type").equals("getCollectionGoods")) {
				String user_id=request.getParameter("user_id");
				List<Goods> list=new GoodsDaoImpl().getCollectionGoodsByUserId(user_id);
				out.print(JSONArray.fromObject(list).toString());
			}
			//获取发布的goods
			if (request.getParameter("type").equals("getPublishGoods")) {
				String user_id=request.getParameter("user_id");
				List<Goods> list=new GoodsDaoImpl().getPublishGoodsByUserId(user_id);
				out.print(JSONArray.fromObject(list).toString());
			}
			//更新goods状态
			if (request.getParameter("type").equals("updateGoodsState")) {
				String goodsId=request.getParameter("goods_id");
				String userId=request.getParameter("user_id");
				int i=new GoodsDaoImpl().updateGoodsState(userId, goodsId);
				out.print(i);
			}
			//获取goods State
			if (request.getParameter("type").equals("getGoodsState")) {
				String goodsId=request.getParameter("goods_id");
				int i=new GoodsDaoImpl().getGoodsState(goodsId);
				out.print(i);
			}
			
		}
	}

}
