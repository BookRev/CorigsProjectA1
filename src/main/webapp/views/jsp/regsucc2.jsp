<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<!-- Access the bootstrap Css like this,
		Spring boot will handle the resource mapping automcatically -->
	<link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

	<!--
	<spring:url value="/css/main.css" var="springCss" />
	<link href="${springCss}" rel="stylesheet" />
	 -->
	<c:url value="/css/main.css" var="jstlCss" />
	<link href="${jstlCss}" rel="stylesheet" />

<title>Insert title here</title>
<style type="text/css">
.starter-content {
	font-size: 18px;
}
.starter-template .starter-content h5 {
	font-size: 24px;
}
.starter-template .starter-content h3 {
	font-size: 22px;
}
</style>
</head>
<body class="starter-template">
<nav class="navbar navbar-inverse">
		    <div class="container">
			<div class="navbar-header"> <a class="navbar-brand" href="/sup">Admin Login</a>		      </div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
				   <li class="active"><a href="/home">Home</a></li>
					<li class="active"><a href="/del">Delete Users</a></li>
					<li class="active"><a href="/shows">View Users</a></li>
					<li class="active"><a href="/adminhistory">View History</a></li>
					<li class="active"><a href="/addadmin">Add Admin</a></li>
				</ul>
			</div>
		</div>
</nav>
         <div class="starter-template">
		   <h1>Register Success</h1>
         </div>
<div class="starter-content">
			<h2>Your Username: ${username}</h2>
			<h3>Your Email: ${email}</h3>
<h5>Your Password: ${password}</h5>
</div>

<form:form method="POST"
           action="/home" modelAttribute="noused">
  <input type="submit" value="Home Page"/>
</form:form>


</body>
</html>