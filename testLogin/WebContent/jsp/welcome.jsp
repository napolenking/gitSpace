<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Your Welcome!</title>
</head>
<body>
<%-- 
	<c:choose>
		<c:when test="${sessionScope.user == null}">
			<jsp:forward page="login.jsp"></jsp:forward>
			<%
			response.sendRedirect("login.jsp");
			%>
		</c:when>
		<c:otherwise> --%>
			<form action="/testLogin/Logout" method="post">
				<div>
					<input type="submit" name="cancel" value="退出登录"> <input
						type="submit" name="logout" value="用户注销">
				</div>
			</form>
			<h1>欢迎您，${sessionScope.user.userName},登录成功！</h1>
			<br>
			<h3>当前在线用户列表:</h3>
			<br>
			<c:forEach var="map" items="${requestScope.beanMap}">
				${map.value}<br>
			</c:forEach>
<%-- 		</c:otherwise> --%>
<%-- 	</c:choose> --%>
</body>
</html>