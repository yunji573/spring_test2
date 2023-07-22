<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.0.19/dist/sweetalert2.all.min.js"></script>
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
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<script type="text/javascript" src="/resources/JS/user/userlist.js"></script>
<body>
	<jsp:include page="../AdminHeader.jsp" />

	<div class="container" style="max-width: 700px">
		<div class="m-5 py-3 text-center">
			<h2>회원목록</h2>
		</div>
		<div class="row"></div>
		<hr class="my-8">
		<div>
			<table class="table table-hover">
				<thead>
					 <tr >
			 

						<th>ID</th>
						<th>grade</th>
						<th>nickname</th>
						<th>role</th>

						<th></th>
					</tr>
				</thead>

				<tbody>
					<c:forEach var="item" items="${list }">
					<tr class="table-light">
 
							<td id="userId">${item.id }</td>
							<td>${item.username }</td>
							<td>${item.nickname}</td>

							<td><select class="form-control" id="role" name="role"
								onchange="updateSelectedRole(this)">
									<c:choose>
										<c:when test="${item.role eq 'ROLE_USER' }">
											<option value="ROLE_USER" selected="selected">일반인</option>
										</c:when>
										<c:otherwise>
											<option value="ROLE_USER">일반인</option>
										</c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${item.role eq 'ROLE_CRITIC' }">
											<option value="ROLE_CRITIC" selected="selected">전문가</option>
										</c:when>
										<c:otherwise>
											<option value="ROLE_CRITIC">전문가</option>
										</c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${item.role eq 'ROLE_ADMIN' }">
											<option value="ROLE_ADMIN" selected="selected">관리자</option>
										</c:when>
										<c:otherwise>
											<option value="ROLE_ADMIN">관리자</option>
										</c:otherwise>
									</c:choose>

							</select></td>

							<td><button class="btn btn-outline-warning"
									onclick="roleChange(${item.id })">수정</button></td>

						</tr>

					</c:forEach>
				</tbody>

			</table>
		</div>
		<a href="/user/admin/list/1">&laquo;</a>
		<c:forEach begin="${startPage}" end="${endPage }" var="page">
			<a href="/user/admin/list/${page}">${page}</a>
		</c:forEach>
		<a href="/user/admin/list/${maxPage}">&raquo;</a>
	</div>
	<!-- /container -->

</body>
</html>