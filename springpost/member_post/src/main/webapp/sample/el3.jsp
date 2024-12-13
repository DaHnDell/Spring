<%@page import="java.util.List"%>
<%@ page import = "java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>${10 + 4}</h3>
	<h3>${'10' + 4}</h3>
	<h3>${"10" + 4}</h3>
	<h3>${3.14 + 1}</h3>
	<h3>${3.14 gt 1}</h3>
	<h3>${3.14 eq 1}</h3>
	<h3>${3.14 ge 1}</h3>
	<h3>\${true or false}::${true or false}</h3>
	
	<hr>
	
	<%
		List<String> list = new ArrayList<>();
		list.add("abcd");
		String[] strings = {};
		
		pageContext.setAttribute("list", list);
		pageContext.setAttribute("array", strings);
		pageContext.setAttribute("str1", "");
		pageContext.setAttribute("str2", "abcd");
		pageContext.setAttribute("str3", null);
	%>
	
	<h3>${empty list}</h3>
	<h3>${empty array}</h3>
	<h3>${empty str1}</h3>
	<h3>${empty str2}</h3>
	<h3>${empty str3}</h3>
	
</body>
</html>