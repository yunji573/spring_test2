<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
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
	
	
</head>
<body>
	<jsp:include page="../AdminHeader.jsp" />

	<div class="container" style="max-width: 1200px">
		<div class="m-10 py-3 text-center">
			<h2>상영시간목록</h2>
		</div>
	


		<div class="row">
			<div class="col">
				<button class="btn btn-warning float-end"
					onclick="location.href='/showtime/admin/write'" type="button">new상영시간등록</button>
			</div>
		</div>
		
		<hr class="my-10">
		<div>
			<table class="table">
				<thead>
					<tr>
						<th>ID</th>
						<th>title</th>
						<th>theater</th>
						<th>time</th>
						<th>상영시작일자</th>
						<th>상영종료일자</th>
						<th></th>
					</tr>
				</thead>

				<tbody>
					<c:forEach var="item" items="${list }">
						<tr>

							<td>${item.id }</td>
							<td>${item.title }</td>
							<td>${item.theatername}</td>
							<td>${item.time}</td>
							<td>${item.startDate }</td>
							<td>${item.endDate}</td>

							<td><a href="/showtime/admin/timePrintOne/${item.id}"
								class="btn btn-success">상세</a> <a
								href="/showtime/admin/update/${item.id}" class="btn btn-primary">수정</a>
								<a href="/showtime/admin/delete/${item.id}" class="btn btn-danger">삭제</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</div>
	 
		<a href="/sz/1">&laquo;</a>
		<c:forEach begin="${startPage}" end="${endPage }" var="page">
			<a href="/showtime/admin/timelist/${page}">${page}</a>
		</c:forEach>
		<a href="/showtime/admin/timelist/${maxPage}">&raquo;</a>
	</div>

</body>
</html>