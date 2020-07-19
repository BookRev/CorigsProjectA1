<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<h3>search</h3>
<script>
    function pageRedirect2() {
      window.location.href = "/search2";
    }      
</script> 
<script>
    function pageRedirect3() {
      window.location.href = "/search";
    }      
</script> 
<form:form method="POST"
           action="/search3" modelAttribute="title">
    <table width="259">
        <tr>
            <td width="66"><form:label path="bookname">Title</form:label></td>
            <td width="164"><form:input path="bookname"/></td>
        </tr>           
    </table>
    <p>&nbsp;</p>
    <table width="240">
        <tr>
            <td><input type="button" name="Submit2" onClick="pageRedirect2()" value="I want to upload a picture instead">
        </tr>
        <tr>
            <td><input type="button" name="Submit3" onClick="pageRedirect3()" value="I want to type isbn instead">
        </tr>
        <tr>
            <td><input type="submit" value="Search this name!"/></td>
        </tr>
    </table>
</form:form>
</body>
</html>