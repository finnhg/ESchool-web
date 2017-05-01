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
<!-- 某个用户发布的所有朋友圈 -->
</head>
<body>
	<div class="pd-20">
		<center>
		<table class="table table-border table-bordered table-bg"  >
			<thead>
				<tr>
					<th scope="col" colspan="7">
					<% 
					out.print(request.getParameter("user_name"));
					%>的朋友圈</th>
				</tr>
				<tr class="text-c">
					<th>照片</th>
					<th>朋友圈</th>
					<th>发布时间</th>
					<th>发布人</th>
					<th>点赞数</th>
					<th>评论数</th>
					<th>删除</th>
				</tr>
			</thead>
				<% 
					if(request.getAttribute("List")!=null){
						List<Show> list=(List<Show>)request.getAttribute("List");
						for(Show show:list){
							out.print("<tr class='text-c'>");
							out.print("<td><img width='100px' height='90px' src='upload/"+show.getShowImg()+"'/></td>");
							out.print("<td>"+show.getShowMessage()+"</td>");
							out.print("<td>"+show.getShowTime()+"</td>");
							out.print("<td>"+show.getUserName()+"</td>");
							out.print("<td>"+show.getShowPraise()+"</td>");
							out.print("<td><a href='ShowCommentServlet?type=allcomments&show_id="+show.getShowId()+"'>"+show.getCommentCount()+"</td>");
							out.print("<td><a href='UserServlet?type=deleteshow&show_id="+show.getShowId()+"' onclick=\"if(confirm('确定删除吗?')==false)return false;\">删除</a></td>");
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
				href="UserServlet?type=showshows&user_id=<%=request.getParameter("user_id") %>&currentpageno=<%=currentpageno + 1%>">下一页</a>
			<a href="UserServlet?type=showshows&user_id=<%=request.getParameter("user_id") %>&currentpageno=<%=pageCount%>">尾页</a>
			<%
				} else if (currentpageno > 1 && currentpageno < pageCount) {
			%>
			<a href="UserServlet?type=showshows&user_id=<%=request.getParameter("user_id") %>&currentpageno=1">首页</a> <a
				href="UserServlet?type=showshows&user_id=<%=request.getParameter("user_id") %>&currentpageno=<%=currentpageno - 1%>">上一页</a>
			<a
				href="UserServlet?type=showshows&user_id=<%=request.getParameter("user_id") %>&currentpageno=<%=currentpageno + 1%>">下一页</a>
			<a href="UserServlet?type=showshows&user_id=<%=request.getParameter("user_id") %>&currentpageno=<%=pageCount%>">尾页</a>
			<%
				} else if (currentpageno == pageCount && currentpageno != 1) {
			%>
			<a href="UserServlet?type=showshows&user_id=<%=request.getParameter("user_id") %>&currentpageno=1">首页</a> <a
				href="UserServlet?type=showshows&user_id=<%=request.getParameter("user_id") %>&currentpageno=<%=currentpageno - 1%>">上一页</a>
			<%
				}
			%>
		</center>
		</center>
	</div>
</body>
</html>