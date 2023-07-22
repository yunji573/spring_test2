<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- CSS파일 -->
<link rel="stylesheet" type="text/css"
	href="../../resources/css/movie/review.css">

<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/resources/CSS/darkly.css">
<!-- bootstrap -->
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
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />

<!-- jQuery -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- SweetAlert -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>


<script type="text/javascript" src="/resources/JS/movie/printOne.js"></script>

</style>
</head>
<body>
 <jsp:include page="../userHeader.jsp" /> 
	<div class="container">
		<div class="row mt-5">
			<!-- 이미지 부분 -->
			<div class="column">
				<c:forEach items="${attachedList}" var="file">
					<img src="/upload/${movieDTO.id}/${file.path}" alt="Movie Image"
						width="400" height="500">
				</c:forEach>
			</div>
			<!-- 영화 정보 부분 -->
			<div class="column">

				<div class="card text-bg-warning mb-6" style="max-width: 39rem;">
				 
					<div class="card-body">Title: ${movieDTO.title}</div>
					<div class="card-body">Grade: ${movieDTO.grade}</div>

					<div class="card-body">Synopsis: ${movieDTO.synopsis }</div>
				</div>




			</div>

			<div class="post">
				<div class="text  mt-5">Review</div>
			</div>

			<div class="star-widget">
				<input type="radio" name="rate" id="rate-5" value="5"> <label
					for="rate-5" class="fas fa-star"></label> <input type="radio"
					name="rate" id="rate-4" value="4"> <label for="rate-4"
					class="fas fa-star"></label> <input type="radio" name="rate"
					id="rate-3" value="3"> <label for="rate-3"
					class="fas fa-star"></label> <input type="radio" name="rate"
					id="rate-2" value="2"> <label for="rate-2"
					class="fas fa-star"></label> <input type="radio" name="rate"
					id="rate-1" value="1"> <label for="rate-1"
					class="fas fa-star"></label>

			
				<sec:authorize access="hasRole('ROLE_CRITIC')">
					<div class="textarea">
						<textarea class="form-control"  id="review" name="review" cols="30"
							placeholder="Describe your experience.."></textarea>
					</div>
				</sec:authorize>
				
					<button class="btn btn-danger" onclick="replyWrite(${movieDTO.id})" id="btn_write">리뷰등록</button>
			
			</div>

			<div class="row">
				<div class="col-md-6">

					<div class="card text-bg-warning mb-6 ml-6"
						style="max-width: 36rem;">
					 
						<div class="card-body">
							<h5 class="card-title">전문가평점</h5>
							<p class="card-text">${criticAverage}</p>
						</div>
					</div>

				</div>
				<div class="col-md-6">
					<div class="card text-bg-warning mb-6" style="max-width: 36rem;">
				 
						<div class="card-body">
							<h5 class="card-title">관람객평점</h5>
							<p class="card-text">${userAverage}</p>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="column">
					<table>
						<tr>
							<th>nickname</th>
							<th>review</th>
							<th>rating</th>
						</tr>
						<c:forEach items="${reviewlist}" var="review">
							<tr>
								<td>${review.writerNickname}</td>
								<td>${review.review}</td>
								<td><c:forEach begin="1" end="${review.rating}"
										varStatus="loop">
										<i class="bi bi-star-fill"></i>
									</c:forEach> <c:forEach begin="${review.rating + 1}" end="5"
										varStatus="loop">
										<i class="bi bi-star"></i>
									</c:forEach></td>
							</tr>
						</c:forEach>

					</table>
				</div>
				<div class="column">
					<table>
						<tr>
							<th>nickname</th>
							<th>rating</th>

						</tr>
						<c:forEach items="${reviewUserlist}" var="review">
							<tr>
								<td>${review.writerNickname}</td>
								<td><c:forEach begin="1" end="${review.rating}"
										varStatus="loop">
										<i class="bi bi-star-fill"></i>
									</c:forEach> <c:forEach begin="${review.rating + 1}" end="5"
										varStatus="loop">
										<i class="bi bi-star"></i>
									</c:forEach></td>
							</tr>
						</c:forEach>

					</table>
				</div>
			</div>


		</div>
	</div>

</body>
</html>