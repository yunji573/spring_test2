<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!-- 관리자 상영시간등록 페이지 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
	href="../../resources/css/darkly.css">
<!-- jQuery library -->
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

 


<title></title>
</head>
<body>

	<jsp:include page="../AdminHeader.jsp" />

 
 

	<!-- 영화시간 등록페이지 -->
	<div class="container" style="max-width: 700px; padding-top: 100px;">
		<h2>(관리자)상영시간 등록</h2>
       <div class="row">
			<div class="col">
				<button class="btn btn-warning float-end"
					onclick="location.href='/showtime/admin/timelist/1'" type="button">상영시간목록</button>
			</div>
		</div>

		<div class="pt-5 row text-center">
			<div class="col-sm-12" align="center">
				<form action="/showtime/admin/write" method="post">
					<table class="table col-sm-12">
						<tr>
							<td class="table-info"><label class="control-label col-sm-12"><h5>영화제목</h5></label></td>
							<td><input type="text" class="form-control col-sm-12"
								id="movieId" name="movieId"></td>
									 
							</select></td>
						</tr>
						<tr>
							<td class="table-info"><label class="control-label col-sm-12"><h5>영화관이름</h5></label></td>
							<td>
									<input type="text" class="form-control col-sm-12"
								id="theaterId" name="theaterId"></td>
							</td>
						</tr>
						<tr>
						
						
						
							<td class="table-info"><label class="control-label col-sm-12"><h5>상영시간</h5></label></td>
							<td><input type="checkbox" name="time" value="08:00"> 08:00
								<input type="checkbox" name="time" value="09:30"> 09:30 <input
								type="checkbox" name="time" value="10:30"> 10:30 <input
								type="checkbox" name="time" value="12:00"> 12:00 <input
								type="checkbox" name="time" value="13:30"> 13:30 <input
								type="checkbox" name="time" value="15:00"> 15:00</td>
						<tr>
							<td class="table-info"><label class="control-label col-sm-12"><h5>상영시작일자</h5></label></td>
							<td><input type="date" class="form-control col-sm-12"
								id="startDate" name="startDate"></td>
						</tr>
						<tr>
							<td class="table-info"><label class="control-label col-sm-12"><h5>상영종료일자</h5></label></td>
							<td><input type="date" class="form-control col-sm-12"
								id="endDate" name="endDate"></td>
						</tr>


					</table>
					<button type="submit" class="btn btn-outline-info">상영시간등록</button>
				</form>

			</div>
		</div>
	</div>
 
	
	
	

</body>
</html>