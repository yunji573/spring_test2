<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="../../resources/css/login/login.css">
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
<!-- Bootstrap JS -->
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
	integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootswatch@4.5.2/dist/darkly/bootstrap.min.css"
	integrity="sha384-nNK9n28pDUDDgIiIqZ/MiyO3F4/9vsMtReZK39klb/MtkZI3/LtjSjlmyVPS3KdN"
	crossorigin="anonymous">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div class="wrapper" >
<div class="card">
  <img class="card-img-top" src="../../resources/images/MGM.png" alt="Card image">
  <div class="card-body">
   <form  action="/user/auth" method="post">
				 
				<input type="text" class="form-control" name="username"
					placeholder="username" required="" autofocus="" /> <input
					type="password" class="form-control" name="password"
					placeholder="Password" required="" />

				<button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
				<a href="user/register" class="btn btn-lg btn-primary btn-block">Signup</a>
			</form>
  </div>
</div>
</div> 

</body>
</html>