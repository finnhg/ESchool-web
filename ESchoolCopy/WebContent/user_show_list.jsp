<%@page import="com.wangc.model.Show"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/H.css" rel="stylesheet"  type="text/css"></link>
<link href="css/H-ui.admin.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="js/jquery.min.js" ></script>
<title>Insert title here</title>
</head>
<body>
	<div class="pd-20">
		<center>
			<table class="table table-border table-bordered table-bg">
				<thead>
					<tr>
						<th scope="col" colspan="7" >朋友圈</th>
					</tr>
					<tr class="text-c">
						<th>图片</th>
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
							out.print("<td><img width='100px' height='90px' src='upload/"+show.getShowImg()+"' /></td>");
							out.print("<td>"+show.getShowMessage()+"</td>");
							out.print("<td>"+show.getShowTime()+"</td>");
							out.print("<td>"+show.getUserName()+"</td>");
							out.print("<td>"+show.getShowPraise()+"</td>");
							out.print("<td><a href='ShowCommentServlet?type="+show.getShowId()+"'>"+show.getCommentCount()+"</a></td>");
							out.print("<td><a href='ShowsServlet?type=delete&showId="+show.getShowId()+"' onclick=\"if(confirm('确定删除吗?')==false)return false;\" >删除</a></td>");
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
					//  1/1
					if(currentPageNo == 1 && pageCount == 1){
						out.print(currentPageNo+"/"+pageCount);
					}
					//  1/5  下一页   尾页
					if(currentPageNo == 1 && pageCount > 1){
						out.print(currentPageNo+"/"+pageCount);
						%>
						<a href="ShowsServlet?type=pages&currentPageNo=<%= currentPageNo+1 %>" >下一页</a>
						<a href="ShowsServlet?type=pages&currentPageNo=<%= pageCount %>" >尾页</a>
						<%
					}
					//  首页  上一页   2/5   下一页  尾页  
					if(currentPageNo > 1 && pageCount > 1 && currentPageNo < pageCount){
						%>
						<a href="ShowsServlet?type=pages&currentPageNo=<%= 1 %>" >首页</a>
						<a href="ShowsServlet?type=pages&currentPageNo=<%= currentPageNo-1 %>" >上一页</a>
						<%
						out.print(currentPageNo+"/"+pageCount);
						%>
						<a href="ShowsServlet?type=pages&currentPageNo=<%= currentPageNo+1 %>" >下一页</a>
						<a href="ShowsServlet?type=pages&currentPageNo=<%= pageCount %>" >尾页</a>
						<%
					}
					//   首页  上一页    5/5
					if(currentPageNo == pageCount && pageCount > 1){
						%>
						<a href="ShowsServlet?type=pages&currentPageNo=1" >首页</a>
						<a href="ShowsServlet?type=pages&currentPageNo=<%=currentPageNo-1 %>" >上一页</a>
						<%
						out.print(currentPageNo+"/"+pageCount);
					}
				%>
			</center>
		</center>
	</div>
</body>
</html>