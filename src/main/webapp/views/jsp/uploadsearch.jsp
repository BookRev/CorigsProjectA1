<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.br.dao.Barcode"%>
<html>
<head>
<!-- Access the bootstrap Css like this,
		Spring boot will handle the resource mapping automcatically -->
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
					<li class="active"><a href="/search">Search books</a></li>
					<li class="active"><a href="/history">My history</a></li>
					<li class="active"><a href="/favorite">My Favorites</a></li>
				</ul>
			</div>
		</div>
	</nav>
<h3>Upload a barcode file (this will need much more time)</h3>
<script>
    function pageRedirect() {
      window.location.href = "/search";
    }      
</script> 
<script>
    function pageRedirect2() {
      window.location.href = "/search3";
    }      
</script> 
<form:form method="post" action="/search2" modelAttribute="search" enctype="multipart/form-data">
    <form:input type="file" name="uploadFile" value="upload a file" path="barpic"/>
    <br/><br/>
    <input type="button" name="Submit2" onclick="pageRedirect()" value="I want to type the isbn">
    <input type="button" name="Submit3" onclick="pageRedirect2()" value="I want to type the title">
    <input type="submit" value="upload"/>
</form:form>
</body>
</html>