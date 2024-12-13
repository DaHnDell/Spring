<%@page import="vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		Member member = Member
			.builder()
			.id("javaman")
			.name("자바맨")
			.email("javaman@naver.com")
			.build();
		System.out.println(page == this);
		pageContext.setAttribute("member", member);
		request.setAttribute("number", 20);
		session.setAttribute("number", 30);
		application.setAttribute("number", 40);
	%>
	<h2>${member}</h2>
	<h2>${member.num}</h2>
	<h2>${member.id}</h2>
	<h2>${member.getId()}</h2>
	<h2>${member.name}</h2>
	<h2>${member.email}</h2>
	
	<jsp:forward page="el2.jsp"/> 
	<%
		// response.sendRedirect("el2.jsp");
	%>
	
</body>
</html>