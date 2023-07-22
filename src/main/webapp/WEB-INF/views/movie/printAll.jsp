<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- 관리자 메인 페이지 -->
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
 

<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.22/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.22/css/jquery.dataTables.min.css">
<link rel="stylesheet" type="text/css" href="../../resources/css/printall.css">
<title>시네마천국</title>
</head>
<body>
 	
	<jsp:include page="../userHeader.jsp" />


	<!-- 메뉴바 밑 컨테이너 -->
	<div class="container">
		<section class="bg-light pt-5 pb-5 shadow-sm">
			<div class="container">
				<div class="row pt-5"></div>

				<!-- 영화정보 리스트출력 -->

				<div class="row">
					<c:forEach var="item" items="${list }">
						<div class="col-lg-4 mb-3  align-items-stretch">
							<div class="card">
								<img src="/upload/${item.id}/${item.imaname}"
									class="card-img-top" alt="Card Image" width="300" height="500">

								<div class="card-body d-flex flex-column">
									<h5 class="card-title">
										<p class="text-body">${item.title}<span class="badge bg-warning">${item.grade} </span></p>
									</h5>
										<div style="display: flex;">
										<c:forEach begin="1" end="${item.averageRating}"
											varStatus="loop">
											<i class="bi bi-star-fill star"></i>
										</c:forEach>
										<c:forEach begin="${item.averageRating + 1}" end="5"
											varStatus="loop">
											<i class="bi bi-star star"></i>
										</c:forEach>	<p class="text-success"> (전체평점: ${item.averageRating})</p>
									</div>
									 
 

									<a href="/movie/printOne/${item.id}"
										id="anchor_title_${item.id}"
										class="btn btn-light text-white mt-auto align-self-start">details</a>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
				<div>
					<ul class="pagination">
						<li class="page-item"><a class="page-link"
							href="/movie/printAll/1">&laquo;</a></li>

						<c:forEach begin="${startPage}" end="${endPage }" var="page">
							<li class="page-item"><a class="page-link"
								href="/movie/printAll/${page}">${page}</a></li>
						</c:forEach>
						<li class="page-item"><a class="page-link"
							href="/movie/printAll/${maxPage}">&raquo;</a></li>
					</ul>
				</div>
			</div>
		</section>


	</div>

</body>



</html>