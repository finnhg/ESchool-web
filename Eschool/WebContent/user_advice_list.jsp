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
<!-- 用户的反馈意见 -->
</head>
<body>
	<div class="pd-20">
		<center>
		<table class="table table-border table-bordered table-bg"  >
			<thead>
				<tr>
					<th scope="col" colspan="5">用户反馈意见</th>
				</tr>
				<tr class="text-c">
					<th >用户名</th>
					<th >关键字</th>
					<th >意见内容</th>
					<th >反馈时间</th>
					<th >删除</th>
				</tr>
			</thead>
				<% 
					if(request.getAttribute("List")!=null){
						List<UserAdvice> list=(List<UserAdvice>)request.getAttribute("List");
						for(UserAdvice userAdvice:list){
							out.print("<tr class='text-c'>");
							out.print("<td>"+userAdvice.getUserName()+"</td>");
							out.print("<td>"+userAdvice.getAdviceKeyword()+"</td>");
							out.print("<td>"+userAdvice.getUserAdvice()+"</td>");
							out.print("<td>"+userAdvice.getAdviceTime()+"</td>");
							out.print("<td><a href='UserAdviceServlet?type=delete&advice_id="+userAdvice.getAdviceId()+"' onclick=\"if(confirm('确定删除吗?')==false)return false;\">删除</a></td>");
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
			%>
			<a href="UserAdviceServlet?type=pages&currentpageno=<%=currentpageno + 1%>">下一页</a>
			<a href="UserAdviceServlet?type=pages&currentpageno=<%=pageCount%>">尾页</a>
			<%
				} else if (currentpageno > 1 && currentpageno < pageCount) {
			%>
			<a href="UserAdviceServlet?type=pages&currentpageno=1">首页</a> 
			<a href="UserAdviceServlet?type=pages&currentpageno=<%=currentpageno - 1%>">上一页</a>
			<a href="UserAdviceServlet?type=pages&currentpageno=<%=currentpageno + 1%>">下一页</a>
			<a href="UserAdviceServlet?type=pages&currentpageno=<%=pageCount%>">尾页</a>
			<%
				} else if (currentpageno == pageCount && currentpageno != 1) {
			%>
			<a href="UserAdviceServlet?type=pages&currentpageno=1">首页</a> 
			<a href="UserAdviceServlet?type=pages&currentpageno=<%=currentpageno - 1%>">上一页</a>
			<%
				}
			%>
		</center>
		</center>
	</div>
</body>
</html>