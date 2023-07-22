<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<!-- jquery추가  -->
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

	<div class="container" style="max-width: 700px">
		<div class="m-5 py-3 text-center">
			<h2>상영시간목록</h2>
		</div>



		<div class="row">
			<div class="col">
				<button class="btn btn-warning float-end"
					onclick="location.href='/theater/admin/write'" type="button">극장등록</button>
			</div>
		</div>

		<hr class="my-8">
		<div>
			<table class="table">
				<thead>
					<tr>
						<th>ID</th>
						<th>title</th>
						<th>theater</th>
						<th>time</th>
						<th></th>
					</tr>
				</thead>

				<tbody>
					<c:forEach var="item" items="${list }">
						<tr>

							<td>${item.id }</td>
							<td>${item.name }</td>
							<td>${item.location}</td>
							<td>${item.phone}</td>


							<td><a href="/theater/admin/theaterpricnone/${item.id}"
								class="btn btn-success">상세</a> <a
								href="/theater/admin/update/${item.id}" class="btn btn-primary">수정</a>
								<a href="/theater/admin/delete/${item.id}"
								class="btn btn-danger">삭제</a></td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</div>

		<a href="/sz/1">&laquo;</a>
		<c:forEach begin="${startPage}" end="${endPage }" var="page">
			<a href="/movie/admin/list/${page}">${page}</a>
		</c:forEach>
		<a href="/showtiem/admin/timelist/${maxPage}">&raquo;</a>
	</div>

</body>
</html>