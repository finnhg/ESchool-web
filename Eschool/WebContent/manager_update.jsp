<%@page import="com.just.model.Manager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.just.control.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 更新管理员信息 -->
<link href="css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="css/member-add.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
/* 手机号的正则表达式 */
function isTel(str) {
	var reg = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
	return reg.test(str);
}

/* JS验证表单*/
$(document).ready(function() {
	
	$("#manager_name").change(function(){
		if ($("#manager_name").val()!=null) {
			$("#span2").html("&nbsp;&nbsp;*");
			$("#span2").css({color:"black"});
		}
	})
	
	$("#manager_pwd").change(function(){
		if ($("#manager_pwd").val()!=null) {
			$("#span3").html("&nbsp;&nbsp;*");
			$("#span3").css({color:"black"});
		}
	})
	 $("#manager_phone").change(function(){
		if (!isTel($("#manager_phone").val())) {
			$("#span4").html("&nbsp;&nbsp*手机格式不正确");
			$("#span4").css({color : "red"});
			$("#span4").focus();
		}else{
			$("#span4").html("&nbsp;&nbsp*手机可用");
			$("#span4").css({color : "green"});
		}
	}) 
	
	
})


/* 验证输入框是否为空 */
function judge() {
	var manager_id = document.getElementById("manager_id");
	var manager_name = document.getElementById("manager_name");
	var manager_pwd = document.getElementById("manager_pwd");
	var manager_phone=document.getElementById("manager_phone");
	if (manager_id.value == "") {
		$("#span1").html("&nbsp;&nbsp*Id不能为空");
		$("#span1").css({color : "red"});
		$("#manager_id").focus();
		return false;
	}

	if (manager_name.value == "") {
		$("#span2").html("&nbsp;&nbsp*姓名不能为空");
		$("#span2").css({color : "red"});
		$("#manager_name").focus();
		return false;
	}
	if (manager_pwd.value == "") {
		$("#span3").html("&nbsp;&nbsp*密码不能为空");
		$("#span3").css({color : "red"});
		$("#manager_pwd").focus();
		return false;
	}
	if (manager_phone.value=="") {
		$("#span4").html("&nbsp;&nbsp*电话不能为空");
		$("#span4").css({color:"red"});
		$("#manager_phone").focus();
		return false;
	}
	if (!isTel($("#manager_phone").val())) {
		$("#span4").html("&nbsp;&nbsp*手机格式不正确");
		$("#span4").css({color : "red"});
		$("#span4").focus();
		return false;
	}
	return true;
}
</script>
</head>
<body>
	<%
		Manager manager=(Manager)request.getAttribute("Manager");
	%>
	
	<form action="ManagerServlet?type=confirmupdate" method="post" class="form form-horizontal" enctype="multipart/form-data">
		
		<!-- Id -->
		<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>账号：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" id="manager_id" name="manager_id" readonly="readonly" 
					value="<%=manager.getManagerId()%>"/>
				</div>
				<div class="col-4">
					<span id="span1">&nbsp;&nbsp;*</span>
				</div>
		</div>
		
		<!-- 用户名 -->
		<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>姓名：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" id="manager_name" name="manager_name" 
					value="<%=manager.getManagerName() %>"/>
				</div>
				<div class="col-4">
					<span id="span2">&nbsp;&nbsp;*</span>
				</div>
		</div>
		
		<!-- 密码 -->
		<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>密码：</label>
				<div class="formControls col-5">
					<input type="password" class="input-text" id="manager_pwd" value=<%=manager.getManagerPwd() %> 
					name="manager_pwd"/>
				</div>
				<div class="col-4">
					<span id="span3">&nbsp;&nbsp;*</span>
				</div>
		</div>
		
		
			<!-- 性别 -->
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red"></span>性别：</label>
				<div class="formControls col-5 skin-minimal">
					<div class="radio-box">
						<input type="radio" id="sex-1" name="manager_gender"
							checked="checked" value="男" /> <label for="sex-1">男</label>
					</div>
					<div class="radio-box">
						<input type="radio" id="sex-2" name="manager_gender" value="女" />
						<label for="sex-2">女</label>
					</div>
					<script>
					<%String gender=manager.getManagerGender(); 
					if(gender.equals("男")){
					%>
						document.getElementsByName("manager_gender")[0].checked="checked";
					<%
					}else{
					%>
						document.getElementsByName("manager_gender")[1].checked="checked";
					<%
						}
					%>
					</script>
				</div>
				<div class="col-4"></div>
			</div>
		
		<!-- 电话-->
		<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>电话：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" id="manager_phone" value=<%=manager.getManagerPhone()%> name="manager_phone" />
				</div>
				<div class="col-4">
					<span id="span4">&nbsp;&nbsp;*</span>
				</div>
		</div>
		
			<!-- 头像-->
			<div class="row cl">
				<label class="form-label col-3">头像：</label>
				<div class="formControls col-5">
					<input type="file" class="input-text" name="manager_pic" value=<%= manager.getManagerPic() %>/>
				</div>
				<div class="col-4"></div>
			</div>
		
		
			<!-- 提交按钮-->
			<div class="row cl">
				<div class="col-9 col-offset-3"></div>
				<input class="btn btn-primary radius" type="submit" id="submit"
					onclick="return judge()" name="submit"
					value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
			</div>
		
	</form>
</body>
</html>