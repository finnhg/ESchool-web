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
<!-- 所有用户 -->
</head>
<body>
	<div class="pd-20">
		<center>
		<table class="table table-border table-bordered table-bg"  >
			<thead>
				<tr>
					<th scope="col" colspan="11">用户列表</th>
				</tr>
				<tr class="text-c">
					<th >照片</th>
					<th >用户名</th>
					<th >性别</th>
					<th >电话</th>
					<th >学校</th>
					<th>生日</th>
					<th>个性签名</th>
					<th >等级</th>
					<th >商品</th>
					<th >朋友圈</th>
					<th >收藏</th>
				</tr>
			</thead>
				<%
					if(request.getAttribute("List")!=null){
						List<User> list=(List<User>)request.getAttribute("List");
						for(User user:list){
							out.print("<tr class='text-c'>");
							out.print("<td><img width='100px' height='90px' src='upload/"+user.getUserPic()+"'/></td>");
							out.print("<td>"+user.getUserName()+"</td>");
							out.print("<td>"+user.getUserGender()+"</td>");
							out.print("<td>"+user.getUserPhone()+"</td>");
							out.print("<td>"+user.getUserSchool()+"</td>");
							out.print("<td>"+user.getUserBirthdate()+"</td>");
							out.print("<td>"+user.getUserSignature()+"</td>");
							out.print("<td>"+user.getUserDegree()+"</td>");
							out.print("<td><a href='UserServlet?type=showgoods&user_id="+user.getUserId()+"'>"+user.getGoodsCount()+"</td>");
							out.print("<td><a href='UserServlet?type=showshows&user_id="+user.getUserId()+"'>"+user.getShowsCount()+"</td>");
							out.print("<td><a href='UserServlet?type=showcollections&user_id="+user.getUserId()+"'>"+user.getCollectionsCount()+"</td>");
							out.print("</tr>");
						}
					}
				%>
		</table>
		<center>
			<% 
				int currentpageno = 1;
				if (request.getParameter("currentpageno") != null) {
					currentpageno = Integer.parseInt(request.getParameter("currentpageno"));
				}
				int pageCount = 0;
				if (request.getParameter("pageCount") != null) {
					pageCount = Integer.parseInt(request.getParameter("pageCount"));
				}

				out.print(currentpageno + "/" + pageCount + "&nbsp;&nbsp;");
				if (currentpageno == 1 && pageCount != 1) {
			%><a
				href="UserServlet?type=pages&currentpageno=<%=currentpageno + 1%>">下一页</a>
			<a href="UserServlet?type=pages&currentpageno=<%=pageCount%>">尾页</a>
			<%
				} else if (currentpageno > 1 && currentpageno < pageCount) {
			%>
			<a href="UserServlet?type=pages&currentpageno=1">首页</a> <a
				href="UserServlet?type=pages&currentpageno=<%=currentpageno - 1%>">上一页</a>
			<a
				href="UserServlet?type=pages&currentpageno=<%=currentpageno + 1%>">下一页</a>
			<a href="UserServlet?type=pages&currentpageno=<%=pageCount%>">尾页</a>
			<%
				} else if (currentpageno == pageCount && currentpageno != 1) {
			%>
			<a href="UserServlet?type=pages&currentpageno=1">首页</a> <a
				href="UserServlet?type=pages&currentpageno=<%=currentpageno - 1%>">上一页</a>
			<%
				}
			%>
		</center>
		</center>
	</div>
</body>
</html>