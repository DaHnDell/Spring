<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ 
		include file = "header.jsp"
	%>
	<h1>outer.jsp</h1>
	<%
		String num = request.getParameter("num");
	%>
	<h3><%= num %></h3>
	<%
	%>
	
</body>
</html>