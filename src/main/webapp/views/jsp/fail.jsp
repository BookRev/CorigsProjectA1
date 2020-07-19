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
				<a class="navbar-brand" href="/home">Home</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
				</ul>
			</div>
		</div>
	</nav>
         <div class="starter-template">
		   <h1>${type} Fail</h1>
         </div>
			<h2>${errormess}</h2>
  

<form:form method="POST"
           action="/home" modelAttribute="noused">
  <input type="button" name="Submit1" onclick="javascript:history.go(-1);" value="Back">
  <input type="submit" value="Home Page"/>
</form:form>


</body>
</html>