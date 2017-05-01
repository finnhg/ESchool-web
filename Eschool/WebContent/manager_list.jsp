<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List" import="com.just.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/H.css" rel="stylesheet" type="text/css" />
<link href="css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<title>Insert title here</title>
<!-- 管理员列表 -->
</head>
<body>
	<div class="pd-20">
		<center>
		<table class="table table-border table-bordered table-bg"  >
			<thead>
				<tr>
					<th scope="col" colspan="9">管理员列表</th>
				</tr>
				<tr class="text-c">
					<th >照片</th>
					<th >Id</th>
					<th >姓名</th>
					<th >密码</th>
					<th >性别</th>
					<th >电话</th>
					<th >修改</th>
					<th>删除</th>
				</tr>
			</thead>
				<% 
					if(request.getAttribute("List")!=null){
						List<Manager> list=(List<Manager>)request.getAttribute("List");
						for(Manager manager:list){
							out.print("<tr class='text-c'>");
							out.print("<td><img width='100px' height='90px' src='upload/"+manager.getManagerPic()+"'/></td>");
							out.print("<td>"+manager.getManagerId()+"</td>");
							out.print("<td>"+manager.getManagerName()+"</td>");
							out.print("<td>"+manager.getManagerPwd()+"</td>");
							out.print("<td>"+manager.getManagerGender()+"</td>");
							out.print("<td>"+manager.getManagerPhone()+"</td>");
							out.print("<td><a href='ManagerServlet?type=update&id="+manager.getManagerId()+"'>修改</a></td>");
							out.print("<td><a href='ManagerServlet?type=delete&id="+manager.getManagerId()+"' onclick=\"if(confirm('确定删除吗?')==false)return false;\">删除</a></td>");
							out.print("</tr>");
						}
					}
				%>
		</table>
		<center>
			<%
				int currentpageno = 1;
				if (request.getParameter("currentpageno") != null) {
					currentpageno = Integer.parseInt(request
							.getParameter("currentpageno"));
				}
				int pageCount = 0;
				if (request.getParameter("pageCount") != null) {
					pageCount = Integer.parseInt(request.getParameter("pageCount"));
				}

				out.print(currentpageno + "/" + pageCount + "&nbsp;&nbsp;");
				if (currentpageno == 1 && pageCount != 1) {
			%><a
				href="ManagerServlet?type=pages&currentpageno=<%=currentpageno + 1%>">下一页</a>
			<a href="ManagerServlet?type=pages&currentpageno=<%=pageCount%>">尾页</a>
			<%
				} else if (currentpageno > 1 && currentpageno < pageCount) {
			%>
			<a href="ManagerServlet?type=pages&currentpageno=1">首页</a> <a
				href="ManagerServlet?type=pages&currentpageno=<%=currentpageno - 1%>">上一页</a>
			<a
				href="ManagerServlet?type=pages&currentpageno=<%=currentpageno + 1%>">下一页</a>
			<a href="ManagerServlet?type=pages&currentpageno=<%=pageCount%>">尾页</a>
			<%
				} else if (currentpageno == pageCount && currentpageno != 1) {
			%>
			<a href="ManagerServlet?type=pages&currentpageno=1">首页</a> <a
				href="ManagerServlet?type=pages&currentpageno=<%=currentpageno - 1%>">上一页</a>
			<%
				}
			%>
		</center>
		</center>
	</div>
</body>
</html>