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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<!-- 구글 폰트 -->
<link
	href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Bowlby+One+SC&family=Bungee&family=Noto+Sans+KR:wght@400;500&display=swap"
	rel="stylesheet">
 

 
<title></title>
</head>
<body>
<jsp:include page="../AdminHeader.jsp" />
 

	<!-- 메뉴바 밑 컨테이너 -->
	<div class="container-fluid">
		<hr>
		<div class="container"></div>
		<hr>
	</div>


	<!-- 영화 등록 페이지 -->
	<div class="container">
		<h2>영화등록</h2>

		<span></span><br> <br> <br> <br>
		<div class="row text-center">
			<div class="col-sm-12" align="center">
				<form action="/movie/admin/write" method="post"
					enctype="multipart/form-data">
					<table class="table col-sm-12">
						<tr>
							<td><label class="control-label col-sm-12">Title</label></td>
							<td><input type="text" class="form-control col-sm-12"
								id="title" name="title"></td>
						</tr>
						<tr>
							<td><label class="control-label col-sm-12">Synopsis</label></td>
							<td><input type="text" class="form-control" id="synopsis"
								name="synopsis"></td>
						</tr>
						<tr>
							<td><label class="control-label col-sm-12">영화등급</label></td>
							<td><select class="form-control" id="grade" name="grade">
									<option value="">등급 선택</option>
									<option value="전체관람가">전체관람가</option>
									<option value="12세관람가">12세관람가</option>
									<option value="15세관람가">15세관람가</option>
									<option value="청소년관람불가">청소년관람불가</option>
							</select></td>
						</tr>
						<tr>
							<td><label class="control-label col-sm-12">image</label></td>
							<td><input type="file" class="form-control" name="file"
								multiple="multiple"></td>
						</tr>

					</table>
					<button type="submit" class="btn btn-outline-info">영화등록하기</button>
				</form>
			</div>
		</div>
	</div>


</body>
</html>