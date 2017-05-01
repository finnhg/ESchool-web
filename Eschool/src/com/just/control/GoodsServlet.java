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
import com.just.model.Goods;

/**商品Servlet
 * Servlet implementation class GoodsServlet
 */
@WebServlet("/GoodsServlet")
public class GoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public GoodsServlet() {
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
			//分页显示所有商品
			if (request.getParameter("type").equals("pages")) {
				int pageSize=3;
				String pageno = request.getParameter("currentpageno");
				int currentpageno = 1;
				if (pageno != null) {
					currentpageno = Integer.parseInt(pageno);
				} else {
					currentpageno = 1;
				}
				List<Goods> list=new GoodsDaoImpl().getGoodsBypage(currentpageno, pageSize);
				//System.out.println(list.size());
				request.setAttribute("List", list);
				request.getRequestDispatcher("goods_list.jsp?currentpageno="+currentpageno+ "&pageCount=" + GoodsDaoImpl.getPageCount(pageSize)+"").forward(request, response);
			}
			//删除商品
			if (request.getParameter("type").equals("delete")) {
				String goods_id=request.getParameter("goods_id");
				int i=new GoodsDaoImpl().deleteGoods(goods_id);
				if(i>0){
					out.print("<script>alert('删除成功！');window.location.href='GoodsServlet?type=pages&currentpageno=1'</script>");
				}else {
					out.print("<script>alert('删除失败！');");
				}
			}
			
		}
	}

}
