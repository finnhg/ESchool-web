package com.just.json;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class UploadUserPicServlet
 */
@WebServlet("/UploadUserPicServlet")
public class UploadUserPicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UploadUserPicServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 设置处理请求参数的编码格式
		response.setContentType("text/html;charset=UTF-8"); // 设置Content-Type字段值
		PrintWriter out = response.getWriter();

		//从request中获取参数id
		if (request.getParameter("type")!=null) {
			int user_id=Integer.parseInt(request.getParameter("user_id"));
			String user_pic=request.getParameter("user_pic");
			System.out.println(user_id+","+user_pic);
			//将用户头像名保存到数据库
			//int i=new UserDaoImpl().uploadPicName(user_id,user_pic);
		}
		
		
		// 下面的代码开始使用Commons-UploadFile组件处理上传的文件数据
		FileItemFactory factory = new DiskFileItemFactory(); // 建立FileItemFactory对象
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		
		// 分析请求，并得到上传文件的FileItem对象
					List<FileItem> items = null;
					try {
						items = upload.parseRequest(request);
					} catch (FileUploadException e) {
						e.printStackTrace();
					}
					// 从web.xml文件中的参数中得到上传文件的路径
					// String uploadPath = "d:\\upload\\";
					String uploadPath = request.getSession().getServletContext()
							.getRealPath("")
							+ "\\upload\\";
					System.out.println(uploadPath);
					out.print(uploadPath);
					File file = new File(uploadPath);
					if (!file.exists()) {
						file.mkdir();
					}
					String filename1 = "";
					String filename2 = ""; // 上传文件保存到服务器的文件名
					InputStream in = null; // 当前上传文件的InputStream对象
					// 循环处理上传文件
					for (FileItem item : items) {
						// 处理普通的表单域
						if (item.isFormField()) {
							if (item.getFieldName().equals("filename")) {
								// 如果新文件不为空，将其保存在filename中
								if (!item.getString().equals(""))
									filename1 = item.getString("UTF-8");
							}
						}
						// 处理上传文件
						else if (item.getName() != null && !item.getName().equals("")) {
							// 从客户端发送过来的上传文件路径中截取文件名
							filename2 = item.getName().substring(
									item.getName().lastIndexOf("\\") + 1);
							in = item.getInputStream(); // 得到上传文件的InputStream对象
						}
					}
					// 将路径和上传文件名组合成完整的服务端路径
					filename2 = uploadPath + filename2;
					// 如果服务器已经存在和上传文件同名的文件，则输出提示信息
					if (new File(filename2).exists()) {
						new File(filename2).delete();
					}
					// 开始上传文件
					if (!filename2.equals("")) {
						// 用FileOutputStream打开服务端的上传文件
						FileOutputStream fos = new FileOutputStream(filename2);
						byte[] buffer = new byte[8192]; // 每次读8K字节
						int count = 0;
						// 开始读取上传文件的字节，并将其输出到服务端的上传文件输出流中
						while ((count = in.read(buffer)) > 0) {
							fos.write(buffer, 0, count); // 向服务端文件写入字节流
						}
						fos.close(); // 关闭FileOutputStream对象
						in.close(); // InputStream对象
						out.println("<br/>文件名称描述:" + filename1);
						out.println("<br/>文件上传成功!" + filename2);

					}
	}

}
