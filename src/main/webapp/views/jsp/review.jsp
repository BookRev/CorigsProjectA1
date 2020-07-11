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
.h6 {
	font-size: 14px;
}
.h7 {
	font-size: 14px;
}
.h7 {
	font-family: Georgia, "Times New Roman", Times, serif;
}
</style>
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
<h3>Book information</h3>

<table width="838" height="140">
        <tr>
  <td width="789">
 
    <div class="messagee">
    <img src = ${res.getBookpic()}>
    <h2>${res.getBookname()}</h2>
    <h3>${res.getAuthor()}</h3>
    <h5>ISBN-13: ${res.getIsbn()}</h5>
    </div>
         </td>
        </tr>    
</table>   
           
<c:forEach items="${reviews}" var="rev" varStatus = "uStatus"> 
    <h6>
    <p class="h6">${rates[uStatus.count-1]}</p>
  </h6>
     <h7>
    <p class="h7">${rev} </p>
  </h7>

</c:forEach>

<form:form method="POST"
           action="/newfavorite" modelAttribute="back">
<input type="button" name="Submit1" onclick="javascript:history.go(-3);" value="Back">
<%-- input type="button" name="Submit2" onclick="openWindowWithPost('/newfavorite','','isbn','${res.getIsbn()}')"
value="Mark as favorite and Back"/--%>
<form:input type = "hidden" path="isbn" value = "${res.getIsbn()}"/>
<input type="submit" value="Mark as favorite and Back"/>
</form:form>

<script type="text/javascript">
setTimeout('reload()', 5000);  
window.onload = function() {
    if(!window.location.hash) {
        window.location = window.location + '#loaded';
        window.location.reload();
    }
}
</script> 


  
</body>

</html>
