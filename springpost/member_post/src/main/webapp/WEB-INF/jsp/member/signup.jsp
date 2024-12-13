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
            <h2 class="text-center fw-bold"><i class="fa-solid fa-plus text-primary"></i> SIGN-UP </h2>
            <hr>
            <form class="col-12 col-sm-8 col-md-6 col-lg-5 col-xl-4 col-xxl-3 mx-auto card p-3" name = "frm" method="post">
                <input type="text" class="form-control my-3" id="id" placeholder="Enter ID" name = id>
                <input type="password" class="form-control my-3" id="pw" placeholder="Enter PW" name = pw>
                <input type="text" class="form-control my-3" id="name" placeholder="Enter NAME" name = name>
                <input type="email" class="form-control my-3" id="email" placeholder="Enter EMAIL" name = email>
                <input type="text" class="form-control my-3" id="address" placeholder="Enter Detailed ADDRESS" name = address>
                <div class="input-group my-3">
                    <input type="text" class="form-control" placeholder="Enter ROAD_NAME">
                    <button class="btn btn-outline-dark" type="button" id="search">Search</button>
                </div>
                <input type="text" class="form-control my-3" id="roadaddress" placeholder="Select after search" name = roadAddress readonly>
                <ul class="list-group">
                </ul>
                <button class="btn btn-outline-dark"> REGISTER </button>
            </form>
            <hr>
        </main>
        <jsp:include page="../common/footer.jsp"/>
    </div>
    <script>
        $("#search").click(function(){
            const keyword = $(this).prev().val();
            if(!keyword) alert("Input Right Address");
            const data = {
                keyword,
                confmKey : 'devU01TX0FVVEgyMDI0MTExMjExNDQyMzExNTIyOTA=',
                currentPage : 1,
                countPerPage : 5,
                resultType : 'json'
            };
            console.log(data, keyword);

            $.ajax({
                url :"https://business.juso.go.kr/addrlink/addrLinkApiJsonp.do",
                type : 'get',
                data,
                dataType : 'jsonp',
                crossDomain : true,
                success : function(data) {
                    console.log(data);
                    function appendLi(){
                        let str = ``;
                        $(data.results.juso).each(function(){
                            str += `<li class="list-group-item"><a class="small text-truncate" href = "#">` 
                                    + this.jibunAddr 
                                    + "</a></li>";
                            $(".list-group").append(str);
                        })
                        $(".list-group").on("click", "a", function() {
                                $("#roadaddress").val($(this).text().trim());
                                $(this).closest("ul").empty();
                        });
                    }
                    appendLi();
                },
                error : function(xhr, msg) {
                    console.log(xhr);
                }
            })
        });
    </script>
</body>
</html>