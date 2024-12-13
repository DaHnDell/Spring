<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix= "c" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../common/head.jsp" />
</head>
<body>
	<div class="wrap">
		<jsp:include page="../common/header.jsp" />
		<hr>
		<main class="container">
			<div class="clearfix my-4">
				<h2 class="fw-bold text-big"><i class="fa-solid fa-feather-pointed text-primary"></i> POST ALTER</h2>
			</div>
            <div class="my-3 col-md-9 mx-auto border border-dark p-4">
				<form method="post" action = 'modify?${criteria.qs2}'>
	                <label for="title" class="form-label mt-3"><i class="fa-solid fa-font text-primary"></i><b> Title </b></label>
	                <input type="text" class="form-control" id="title" placeholder="input title" name="title" value="${post.title}">
	                
	                <label for="content" class="form-label mt-3"><i class="fa-solid fa-pen-to-square text-primary"></i><b> Content </b></label>
	                <textarea rows="15" type="text" class="form-control" id="title" placeholder="input content" name="content"> ${post.content} </textarea>
	                
	                <label for="writer" class="form-label mt-3"><i class="fa-solid fa-user-pen text-primary"></i><b> Writer </b></label>
	                <input type="text" class="form-control" id="writer" placeholder="writer" name="writer" value="${member.id}" readonly>
		
		             <div class="text-center my-5">
	             		<button class="btn btn-outline-dark btn-warning">DO-POST</button>
	                    <a href="list" class="btn btn-outline-warning border-dark">RETURN</a>
		             </div>
		             <input type="hidden" name="pno" value="${post.pno}">
             	</form>
            </div>
		</main>
		<hr>
		<jsp:include page="../common/footer.jsp" />
	</div>
</body>
</html>