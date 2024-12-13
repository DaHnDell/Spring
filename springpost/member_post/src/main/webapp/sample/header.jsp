<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<h1>header.jsp</h1>
	<%	
		String num2 = request.getParameter("num");
	%>
	<h3> outer : <%= num2 %></h3>
