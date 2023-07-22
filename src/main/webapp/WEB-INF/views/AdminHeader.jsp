<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<!-- Bootstrap -->
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
 <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@4.5.2/dist/darkly/bootstrap.min.css" integrity="sha384-nNK9n28pDUDDgIiIqZ/MiyO3F4/9vsMtReZK39klb/MtkZI3/LtjSjlmyVPS3KdN" crossorigin="anonymous">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header>
	
 
<nav class="navbar navbar-expand-lg bg-primary" data-bs-theme="dark">
 
			<div class="container-fluid">
				<a class="navbar-brand" href="#">Admin</a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarCollapse"
					aria-controls="navbarCollapse" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>

	<div class="collapse navbar-collapse" id="navbarCollapse">

					  <ul class="navbar-nav me-auto mb-2 mb-md-0">
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="/movie/printAll/1">Home</a></li>
						<li class="nav-item"><a class="nav-link"
							href="/movie/admin/list/1">영화관리</a></li>
						<li class="nav-item"><a class="nav-link" href="/theater/admin/list/1">극장관리</a></li>
						<li class="nav-item"><a class="nav-link" href="/showtime/admin/timelist/1">상영시간관리</a></li>
						 
						<li class="nav-item"><a class="nav-link"
							href="/user/admin/list/1">회원관리</a></li>
							
						
                     </ul>
						<!-- 오른쪽정렬 메뉴들 -->
                         <ul class="navbar-nav ms-auto mb-2 mb-md-0">
						<sec:authorize access="isAuthenticated()">
							<li class="nav-item"><span class="nav-link">Welcome,
									<sec:authentication property="name" />님
							</span>
							<li class="nav-item"><a class="nav-link" href="/user/logout">Logout</a></li>
						</sec:authorize>

						<sec:authorize access="isAnonymous()">
							<li class="nav-item"><a class="nav-link" href="/user/login">Login</a></li>
						</sec:authorize>
					</ul>
				</div>
			</div>
		</nav>
	</header>
</body>
</html>