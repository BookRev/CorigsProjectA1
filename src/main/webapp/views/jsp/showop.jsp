<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<style type="text/css">
.messagee h2 {
	font-size: 36px;
}
.messagee h3 {
	font-size: 36px;
}
.messagee h5 {
	font-size: 24px;
}
.h1 {
	font-size: 12px;
}
.h1 {
	font-size: 16px;
}
</style>
	
	<link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

	<!--
	<spring:url value="/css/main.css" var="springCss" />
	<link href="${springCss}" rel="stylesheet" />
	 -->
	<c:url value="/css/main.css" var="jstlCss" />
	<link href="${jstlCss}" rel="stylesheet" />
</head>
<body>
	<nav class="navbar navbar-inverse">
		    <div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="/logout">Logout</a>
			</div>
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
<h3>History</h3>


<c:forEach items="${admininfo}" var="thisop" varStatus = "uStatus"> 
    <h1 class="h1"><span class="h1">Opertaion ${uStatus.count}</span></h1>
  <h1 class="h1"><span class="h1">Admin: ${thisop.getAdminname()}</span></h1>
    <h3 class="h1"><span class="h1">Type: ${thisop.getType()}</span></h3>
    <h4 class="h1"><span class="h1">Related Account: ${thisop.getUsername()}</span></h4>
    <p>&nbsp;</p>
    </c:forEach>
   

</body>
</html>