<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
Cookie[] cookies = request.getCookies();
if(session.getAttribute("user")!=null){
		for(Cookie cookie : cookies){
			if(cookie.getName().equals("users")){
				request.getRequestDispatcher("welcome.jsp").forward(request, response);
				break;
				}
			}
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/testLogin/css/index.css" type="text/css" rel="stylesheet" />
<title>用户登陆</title>
</head>
<body>
	<div class="main">
		<div class="title">
			<span>登录</span>
		</div>

		<form class="login-form" method="post" action="/testLogin/Login">
			<!--输入框-->
			<div class="input-content">
				<!--autoFocus-->
				<div>
					<input type="text" autocomplete="off" placeholder="用户名"
						name="userid" required />
				</div>

				<div>
					<input type="password" autocomplete="off" placeholder="登录密码"
						name="password" required maxlength="32" />
				</div>
			</div>
			<!-- 验证码 -->
			<div class="verify-content">
				<p>验证码:</p><input type="text" name="verifyCode" autocomplete="off" placeholder="验证码" required/>
    			<!-- src 此时使用login的post提交方法， 使用框架时，对应验证方法名的路径 -->
    		  <img id="imgObj" alt="验证码" src="/testLogin/VerifyCodeUtils">
    		 <a href="javascript: void(0)" id="exchange">换一张</a>  
			</div>	
				
			<!--登入按钮-->
			<div class="centers">
				<button type="submit" class="enter-btn" id="logins">登录</button>
			</div>
			<!-- 底部链接 -->
			<div class="foor">
				<a href="/testLogin/jsp/lostPasswd.jsp">
					<div class="left">
						<span>忘记密码 ?</span>
					</div>
				</a> 
				<a href="/testLogin/jsp/register.jsp">
					<div class="right">
						<span>注册账户</span>
					</div></a>
			</div>
			
			<!-- 记住密码复选框 -->
			<div class="remember">
    			<p style="display=block;"><input type="checkbox" name="rememberMe" checked="true" value="true"/>&nbsp;记住我?</p>
    		</div>
		</form>
		<div class="error"><span>${errorMsg }</span> </div>
	</div>
</body>

<script src="/testLogin/js/login.js" type="text/javascript"></script>
</html>
