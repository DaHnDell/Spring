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
				<h2 class="fw-bold text-big"><i class="fa-solid fa-feather-pointed text-primary"> </i> WRITE POST</h2>
			</div>
            <div class="my-3 col-md-9 mx-auto border border-secondary p-4 rounded">
				<form method="post" action="write?page=1&${criteria.qs}">
	                <label for="title" class="form-label mt-3"><i class="fa-solid fa-font text-primary"></i><b> Title </b></label>
	                <input type="text" class="form-control" id="title" placeholder="input title" name="title">
	                
	                <label for="content" class="form-label mt-3"><i class="fa-solid fa-pen-to-square text-primary"></i><b> Content </b></label>
	                <textarea rows="15" type="text" class="form-control" id="title" placeholder="input content" name="content"></textarea>
	                
	                <label for="writer" class="form-label mt-3"><i class="fa-solid fa-user-pen text-primary"></i><b> Writer </b></label>
	                <input type="text" class="form-control" id="writer" placeholder="writer" name="writer" value="${member.id}" readonly>

	                <label class="form-label mt-3"><i class="fa-solid fa-paperclip text-primary"></i><b> Attach </b></label>
	                <label for="attach" class="form-label mt-3"><span class="btn btn-outline-dark btn-warning mx-4"> 파일 첨부 </span></label>
	                <span class="mx-2 attach-count-txt">1234</span>
	                <input type="file" id="attach" name="files" class="d-none" multiple>
					<ul class="list-group attach-result">
					</ul>
		             <div class="text-center my-4">
	             		<button class="btn btn-outline-dark btn-warning">DO-POST</button>
	                    <a href="list" class="btn btn-outline-warning border-dark">RETURN</a>
		             </div>
		             
		             <div class="uploaded-input">
		             
		             </div>
             	</form>
            </div>
		</main>
		<hr>
		<jsp:include page="../common/footer.jsp" />
	</div>
	<script type="text/javascript">
		$("#attach").change(function(){
			const url = '${cp}' + 'upload'
			const formData = new FormData();
			const files = this.files;
			
			if(!files){
				$(".attach-count-txt").text("");
				$(".attach-result").empty("");
				return;
			}
			
			for(let i = 0; i < files.length ; i++){
				formData.append("file", files[i]);
			}
			$.post({
				url,
				contentType:false,
				processData:false,
				data:formData
			})
			.done(function(data){
				$(".attach-count-txt").text(data.length + " files attached");
				let str = '';
				let strHidden = '';
				for(let i in data){
					str += `<li class = "list-group-item">\${data[i].origin}</li>`;
					strHidden += `<input type="hidden" name="uuid" value="\${data[i].uuid}">`;
					strHidden += `<input type="hidden" name="origin" value="\${data[i].origin}">`;
					strHidden += `<input type="hidden" name="image" value="\${data[i].image}">`;
					strHidden += `<input type="hidden" name="path" value="\${data[i].path}">`;
				}
				$(".attach-result").html(str);
				$(".uploaded-input").html(strHidden);
				console.log(data);
			});
		});
	</script>
</body>
</html>