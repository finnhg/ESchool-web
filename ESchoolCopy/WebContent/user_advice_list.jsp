<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.wangc.model.UserAdvice"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link href="css/H.css" rel="stylesheet" type="text/css" />
<link href="css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.min.js" ></script>

</head>

<!-- 用户反馈意见页面 -->
<body>
	<div class="pd-20">
		<center>
			<table class="table table-border table-bordered table-bg">
				<thead>
					<tr>
						<th scope="col" colspan="5" >用户反馈意见</th>
					</tr>
					<tr class="text-c">
						<th>用户名</th>
						<th>关键字</th>
						<th>意见内容</th>
						<th>反馈时间</th>
						<th>删除</th>
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
							out.print("<td><a href='UserAdviceServlet?type=delete&advice_id="+userAdvice.getAdviceId()+"'onclick=\"if(confirm('确定删除吗?')==false)return false;\" >删除</a></td>");
							out.print("</tr>");
						}
					}
				%>
			</table>
			<center>
				<%
					int currentPageNo=1;
					if(request.getParameter("currentPageNo")!=null){
						currentPageNo=Integer.parseInt(request.getParameter("currentPageNo"));
					}
					int pageCount=0;
					if(request.getParameter("pageCount")!=null){
						pageCount=Integer.parseInt(request.getParameter("pageCount"));
					}
					if(currentPageNo==1 && pageCount!=1){
						out.print(currentPageNo+"/"+pageCount+"&nbsp;&nbsp");
						%>
						<a href="UserAdviceServlet?type=pages&currentPageNo=<%=currentPageNo+1 %>" >下一页</a>
						<a href="UserAdviceServlet?type=pages&currentPageNo=<%= pageCount %>" >尾页</a>
						<%
					}else if(currentPageNo > 1 && currentPageNo < pageCount){
						%>
						<a href="UserAdviceServlet?type=pages&currentPageNo=1" >首页</a>
						<a href="UserAdviceServlet?type=pages&currentPageNo=<%=currentPageNo-1  %>" >上一页</a>
						<%
							out.print(currentPageNo+"/"+pageCount);
						%>
						<a href="UserAdviceServlet?type=pages&currentPageNo=<%= currentPageNo+1 %>" >下一页</a>
						<a href="UserAdviceServlet?type=pages&currentPageNo=<%= pageCount %>" >尾页</a>
						<%
					}else if(currentPageNo == pageCount && pageCount!=1){
						%>
						<a href="UserAdviceServlet?type=pages&currentPageNo=1" >首页</a>
						<a href="UserAdviceServlet?type=pages&current" >上一页</a>
						<%
					}
					
				%>
			</center>
			
		</center>		
	</div>
</body>
</html>