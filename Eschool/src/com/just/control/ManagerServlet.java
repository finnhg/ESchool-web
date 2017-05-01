package com.just.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.just.dao.impl.ManagerDaoImpl;
import com.just.model.Manager;

/**管理员Servlet
 * Servlet implementation class ManagerServlet
 */
@WebServlet("/ManagerServlet")
@MultipartConfig(location="F:\\webworkspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Eschool\\upload", maxFileSize=1888888)
public class ManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String fileNameExtractorRegex = "filename=\".+\""; //用来匹配文件名； 
    public ManagerServlet() {
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
			
			//检查管理员id是否已经存在
			if (request.getParameter("type").equals("check")) {
				String manager_id=request.getParameter("manager_id");
				Manager manager=new ManagerDaoImpl().getManagerById(manager_id);
				if (manager!=null) {
					out.print(1);
				}else {
					out.print(0);
				}
			}
			//添加管理员
			if (request.getParameter("type").equals("add")) {
				String manager_id=request.getParameter("manager_id");
				String manager_name=request.getParameter("manager_name");
				String manager_pwd=request.getParameter("manager_pwd");
				String manager_gender=request.getParameter("manager_gender");
				String manager_phone=request.getParameter("manager_phone");
				//======================上传头像======================
				List<String> fileNames = new ArrayList<String>();
				String myFileName = null; //用于存放头像的文件名；
				Collection<Part> parts = request.getParts();  //获取表单所有的file
				for (Iterator<Part> iterator = parts.iterator(); iterator.hasNext();) {
					Part part = iterator.next();
					String fileName = getFileName(part);
					if(fileName!=null){
						fileNames.add(fileName);
						part.write(fileName);  //上传；
						myFileName = fileName; //头像的文件名；
					}
				}
				Manager manager=new Manager(manager_id, manager_name, manager_pwd, manager_gender, manager_phone, myFileName);
				int i=new ManagerDaoImpl().addManager(manager);
				if(i>0){
					out.print("<script>alert('添加成功！');window.location.href='manager_add.jsp';</script>");
				}else {
					out.print("<script>alert('添加失败！');window.location.href='manager_add.jsp'</script>");
				}
			}
			//分页查询管理员
			if (request.getParameter("type").equals("pages")) {
				int pageSize=3;
				String pageno = request.getParameter("currentpageno");
				int currentpageno = 1;
				if (pageno != null) {
					currentpageno = Integer.parseInt(pageno);
				} else {
					currentpageno = 1;
				}
				List<Manager> list=new ManagerDaoImpl().getManagerBypage(currentpageno,pageSize);
				request.setAttribute("List", list);
				request.getRequestDispatcher("manager_list.jsp?currentpageno="+currentpageno+ "&pageCount=" + ManagerDaoImpl.getPageCount(pageSize)+"").forward(request, response);
			}
			//删除管理员
			if (request.getParameter("type").equals("delete")) {
				String manager_id=request.getParameter("id");
				int i=new ManagerDaoImpl().deleteManager(manager_id);
				if(i>0){
					out.print("<script>alert('删除成功！');window.location.href='ManagerServlet?type=pages&currentpageno=1'</script>");
				}else {
					out.print("<script>alert('删除失败！');");
				}
			}
			//修改管理员
			if (request.getParameter("type").equals("update")) {
				String manager_id=request.getParameter("id");
				Manager manager=new ManagerDaoImpl().getManagerById(manager_id);
				request.setAttribute("Manager", manager);
				request.getRequestDispatcher("manager_update.jsp").forward(request, response);
			}
			//提交更改的数据
			if (request.getParameter("type").equals("confirmupdate")) {
				String manager_id=request.getParameter("manager_id");
				String manager_name=request.getParameter("manager_name");
				String manager_pwd=request.getParameter("manager_pwd");
				String manager_gender=request.getParameter("manager_gender");
				String manager_phone=request.getParameter("manager_phone");
				List<String> fileNames = new ArrayList<String>();
				String myFileName = null; //用于存放头像的文件名；
				Collection<Part> parts = request.getParts();  //获取表单所有的file
				for (Iterator<Part> iterator = parts.iterator(); iterator.hasNext();) {
					Part part = iterator.next();
					String fileName = getFileName(part);
					if(fileName!=null){
						fileNames.add(fileName);
						part.write(fileName);  //上传；
						myFileName = fileName; //头像的文件名；
					}
				}
				Manager manager=new Manager(manager_id, manager_name, manager_pwd, manager_gender, manager_phone, myFileName);
				int i=new ManagerDaoImpl().updateManager(manager);
				if(i>0){
					out.print("<script>alert('修改成功！');window.location.href='ManagerServlet?type=pages&currentpageno=1';</script>");
				}else {
					out.print("<script>alert('修改失败！');window.location.href='ManagerServlet?type=pages&currentpageno=1'</script>");
				}
			}
			
		}
		
	}
	
	
	//从路径中获取文件的文件名；
		private String getFileName(Part part){
			//获取Header中的content-disposition内容，如果为文件这提取文件名
			String contentName = part.getHeader("content-disposition");
			String fileName = null;
			//通过正则表达式来匹配路径，是否为文件；
			Pattern pattern = Pattern.compile(fileNameExtractorRegex);
			Matcher matcher = pattern.matcher(contentName);
			if (matcher.find()) {
				//提取文件中的文件名;
				fileName = matcher.group();
				fileName = fileName.substring(10, fileName.length() - 1);
			}
			return fileName;
		}

}
