<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css"
	href="../../resources/css/darkly.css">
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
<link rel="stylesheet"
	href=https://bootswatch.com/5/dartly/bootstrap.min.css />
</head>
<body>
	<jsp:include page="../AdminHeader.jsp" />

	<div class="container" style="max-width: 700px">
		<div class="m-5 py-3 text-center">
			<h2>영화목록</h2>
		</div>
		<div class="row">
			<div class="col">
				<button class="btn btn-warning float-end"
					onclick="location.href='/movie/admin/write'" type="button">신규영화등록</button>
			</div>
		</div>
		<hr class="my-8">
		<div>
			<table class="table table-hover">
				<thead>
					<tr class="table-light">
						<th>ID</th>
						<th>title</th>
						<th>grade</th>

						<th></th>
					</tr>
				</thead>

				<tbody>
					<c:forEach var="item" items="${list }">
						<tr>

							<td>${item.id }</td>
							<td>${item.title }</td>
							<td>${item.grade}</td>


							<td><a href="/movie/admin/printOne/${item.id}"
								class="btn btn-success">상세</a> <a
								href="/movie/admin/update/${item.id}" class="btn btn-primary">수정</a>
								<a href="/movie/admin/delete/${item.id}" class="btn btn-danger">삭제</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</div>
		<a href="/movie/admin/list/1">&laquo;</a>
		<c:forEach begin="${startPage}" end="${endPage }" var="page">
			<a href="/movie/admin/list/${page}">${page}</a>
		</c:forEach>
		<a href="/movie/admin/list/${maxPage}">&raquo;</a>
	</div>

</body>
</html>