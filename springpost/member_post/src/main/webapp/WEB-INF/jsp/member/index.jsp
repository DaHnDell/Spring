<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix= "c" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../common/head.jsp" />
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js" integrity="sha512-3j3VU6WC5rPQB4Ld1jnLV7Kd5xr+cq9avvhwqzbH/taCRNURoeEpoPBK9pDyeukwSxwRPJ8fDgvYXd6SkaZ2TA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</head>
<body>
	<div class="wrap">
		<jsp:include page="../common/header.jsp" />
		<hr>
		<main class="container">
			<div class="row">
				<div class="col-md-9">
				    <div class="p-3 text-center mx-auto">
				        <h1 class="fw-bold text-start">index</h1>
				    </div>
				</div>
				<div class="col-md-3 ">
					<div class="p-4 d-grid gap-2">
					<c:if test="${empty member}">
						<a href="signin" class="btn btn-sm btn-primary p-3 small fw-bold">
							<strong> log - in </strong>
						</a>
						<div class="small btn-group btn-group-sm bg-color-none">
							<a href="signup" class="btn btn-outline-dark small fw-small">
								<i> register new </i>
							</a> <a href="signup.html"
								class="btn btn-outline-dark small fw-small"> <i> query account </i>
							</a>
						</div>
						</c:if>
						<c:if test="${not empty member}">
						<div class="container p-4 text-center bg-white border">
							<p>
								welcome home, <strong><a href="mypage.html"
									class="b-2 text-decoration-none">${member.name}</a></strong>!
							</p>
							<div class="small btn-group btn-group-sm bg-color-primary">
								<a href="signout" class="btn btn-outline-dark small fw-small">
									<i> log - out </i>
								</a> 
								<a href="mypage.html" class="btn btn-outline-dark small fw-small"> <i> my - page </i></a>
							</div>
						</div>
						</c:if>
					</div>
				</div>
			</div>
		</main>
		<hr>
		<jsp:include page="../common/footer.jsp" />
	</div>
       <div class="layer-popup">
          <img src="https://image.yes24.com/momo/scmfiles/AdvertFiles/202410/2575682_241011091003.jpg" alt="팝업">
              <p class="clearfix">
                  <span> 오늘은 그만 보기 <input type="checkbox"></span>
                  <a href="#"> 닫기 </a>
              </p>
       </div>
	<script>
		if(!$.cookie("layer")){
			$(".layer-popup").show();
		}	
		
		$(".layer-popup a").click(function(){
			event.preventDefault();
			const checked = $(this).prev().find("input:checkbox").prop("checked");
			if(checked){
				$.cookie('layer', 'yes', {expires:1});
			}
			$(".layer-popup").hide();
		})
	</script>
</body>
</html>