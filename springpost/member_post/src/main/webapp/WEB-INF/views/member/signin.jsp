<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../common/head.jsp"/>
</head>
<body>
    <div class="wrap">
		<jsp:include page="../common/header.jsp"/>
        <main class="container">
            <hr>
            <h2 class="text-center fw-bold "><i class="fa-solid fa-right-to-bracket text-primary"></i> Sign-In </h2>
            <hr>
            <form name = "frm" method="post" class="col-12 col-sm-8 col-md-6 col-lg-5 col-xl-4 col-xxl-3 mx-auto card p-3">
                <input type="text" class="form-control my-3" id="id" placeholder="Enter ID" name = id value="${cookie['remember-id'].value}">
                <input type="password" class="form-control my-3" id="pw" placeholder="Enter PW" name = pw>
                <div class="form-check form-switch my-3">
                    <input class="form-check-input" type="checkbox" id="mySwitch" name="remember-id" value="yes" ${empty cookie['remember-id'] ? '' : checked}>
                    <label class="form-check-label" for="mySwitch"> Remember me </label>
                </div>
                <hr>
                <button class="btn btn-outline-dark" id = "loginbtn"> Log-in </button>
            </form>
            <hr>
        </main>
        <jsp:include page="../common/footer.jsp"/>
        <script>
        	/* if($.cookie("id")) {
        		$("#mySwitch").prop("checked", "true");
        		$("#id").val($.cookie("id"));
        		$("#pw").val($.cookie("pw"));
        	}
        
        	$("#loginbtn").click(function(){
        		if($("#mySwitch").prop("checked")){
        			$.cookie('id', $("#id").val(), {expires:1});
        			$.cookie('pw', $("#pw").val(), {expires:1});
        		}
        	}) */
        </script>
    </div>
</body>
</html>