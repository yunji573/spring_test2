<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@4.5.2/dist/darkly/bootstrap.min.css" integrity="sha384-nNK9n28pDUDDgIiIqZ/MiyO3F4/9vsMtReZK39klb/MtkZI3/LtjSjlmyVPS3KdN" crossorigin="anonymous">

<!-- 아이콘 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="https://use.fontawesome.com/releases/v5.7.2/css/all.css">

<!-- 구글 폰트 -->
<link
	href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Bowlby+One+SC&family=Bungee&family=Noto+Sans+KR:wght@400;500&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
<!-- CSS파일-->

<script async
	src="https://www.googletagmanager.com/gtag/js?id=UA-23019901-1"></script>
<!-- 파비콘 설정-->
<link rel="shortcut icon" href="${path}/resources/img/movie_favicon.ico"
	type="image/x-icon">

<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.22/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.22/css/jquery.dataTables.min.css">

<title>시네마천국</title>
</head>
<body>

	<!-- 

	-->
	<jsp:include page="../userHeader.jsp" />
	<!-- 메뉴바 밑 컨테이너 -->
	<div class="container">
		<section class=" pt-5 pb-5 ">
			<div class="container">
				<div class="row pt-5"></div>

				<!-- 극장정보 리스트출력 -->

				<div class="row">
				<c:forEach items="${list }" var="item">
					<div class="card text-white bg-primary mb-3" style="max-width: 20rem;">
					 
					
						<div class="card-header">${item.name }&nbsp;<span class="badge bg-warning">${item.phone}</span>
						</div>
						<div class="card-body">
						 
							<p class="card-text">${item.location}</p>
					<button type="button" class="btn btn-outline-success"
					 onclick="location.href='/theater/showinglist/${item.id}'">상영시간표</button>
						</div>
						
					</div>
					</c:forEach>
				</div>
			</div>
		</section>
	<div>
					<ul class="pagination">
						<li class="page-item"><a class="page-link"
							href="/theater/admin/list/1">&laquo;</a></li>

						<c:forEach begin="${startPage}" end="${endPage }" var="page">
							<li class="page-item"><a class="page-link"
								href="/theater/admin/list/${page}">${page}</a></li>
						</c:forEach>
						<li class="page-item"><a class="page-link"
							href="/theater/admin/list/${maxPage}">&raquo;</a></li>
					</ul>
				</div>
	
	</div>

</body>



</html>