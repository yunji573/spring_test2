<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- <c:set var="path" value="${pageContext.request.contextPath}" /> --%>

<!-- 관리자 영화 등록 페이지 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- jQuery library -->
 <link rel="stylesheet" type="text/css"
	href="../../resources/css/darkly.css">

 
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



<title>CINE21</title>
</head>
<body>
	<jsp:include page="../AdminHeader.jsp" />
 
	<div class="container-fluid">
		<hr>
		<div class="container"></div>
		<hr>
	</div>


	<!-- 영화 등록 페이지 -->
	<div class="container">
		<h2>(관리자)영화상세정보</h2>

		<span></span><br> <br> <br> <br>
		<div class="row text-center">
			<div class="col-sm-12" align="center">
            
				 <label class="control-label col-sm-12"> <c:forEach
							items="${attachedList}" var="file">
							<img src="/upload/${movieDTO.id}/${file.path}" alt="Movie Image"
								width="200" height="300">
						</c:forEach></label> 

				<table class="table col-sm-12">

					<tr>
						<td><label class="control-label col-sm-12">Title</label></td>
						<td>${movieDTO.title}</td>
					</tr>
					<tr>
						<td><label class="control-label col-sm-12">영화등급</label></td>
						<td>${movieDTO.grade }</td>
					</tr>

					<tr>
						<td><label class="control-label col-sm-12">Synopsis</label></td>
						<td>${movieDTO.synopsis }</td>
					</tr>

 
				</table>
				<a href="/movie/admin/list/1" class="btn btn-outline-info">목록으로</a>

			</div>
		</div>
	</div>

</body>
</html>