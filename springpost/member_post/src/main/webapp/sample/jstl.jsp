<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix= "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:set var="str" value="abcd"/>
	<h3>${pageScope.str}</h3>
	<c:if test="${empty pageScope.str}">
		<h3>페이지스코프에 str이 없습니다</h3>
	</c:if>
	<c:if test="${not empty pageScope.str}">
		<h3>페이지스코프에 str이 있습니다</h3>
	</c:if>
</body>
</html>