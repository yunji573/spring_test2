<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!-- 관리자 영화관등록 페이지 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
	href="../../resources/css/darkly.css">
<!-- jQuery library -->
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script> <!-- jquery추가  -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
	crossorigin="anonymous"></script>

 


<title></title>
</head>
<body>

	<jsp:include page="../AdminHeader.jsp" />

 
 

	<!-- 영화관 등록페이지 -->
	<div class="container" style="max-width: 700px; padding-top: 100px;">
		<h2>(관리자) 영화관상세정보</h2>
     

		<div class="pt-5 row text-center">
			<div class="col-sm-12" align="center">
			 
					<table class="table col-sm-12">
						<tr>
							<td class="table-info"><label class="control-label col-sm-12"><h5>영화관이름</h5></label></td>
							<td>${theaterDTO.name}</td>
									 
							 
						</tr>
						<tr>
							<td class="table-info"><label class="control-label col-sm-12"><h5>location</h5></label></td>
							<td>${theaterDTO.location}</td>
						 
						</tr>
						<tr>
						
						
						
							<td class="table-info"><label class="control-label col-sm-12"><h5>phone</h5></label></td>
							<td>${theaterDTO.phone}</td>
						 


					</table>
					<a href="/theater/admin/list/1" class="btn btn-outline-info">목록으로</a>
				 

			</div>
		</div>
	</div>
 
	
	
	

</body>
</html>