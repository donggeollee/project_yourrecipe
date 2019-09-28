<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>header</title>
<style>
ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
	overflow: hidden;
	background-color: #333;
	position: fixed;
	top: 0;
	width: 100%;
}

li {
	float: left;
}

li a {
	display: block;
	color: white;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
}

.nickname-cont {
	display: block;
	color: gray;
	text-align: right;
	
	padding: 14px 16px;
	text-decoration: underline;
}

/* Change the link color to #111 (black) on hover */
li a:hover {
	background-color: #111;
}
</style>
</head>
<body>
<%
	String nickname = request.getParameter("nickname");
%>
<ul>
	<li><a href="<%=request.getContextPath()%>/1">HOME</a></li>
	<li><a href="<%=request.getContextPath()%>/user/myPage/myLecipe">마이페이지</a></li>  
	<li><a href="<%=request.getContextPath()%>/user/logout">로그아웃</a></li>
	<li><a href="<%=request.getContextPath()%>/article/write">레시피 작성</a></li>
	<li class="nickname-cont"><%=nickname %>님 접속중</li>
</ul>
</body>
</html>