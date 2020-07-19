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
.messagee {
	font-size: 16px;
}
.messagee h4 {
	font-size: 36px;
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
<script type="text/javascript">
function alertName(){
if(!"${canalert}"){
}
else{
alert("Delete success");}
} 
</script> 
<script type="text/javascript">
function alertName2(){
alert("You enter user detail page, which will be recorded in admin history");
} 
</script> 
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
<h3>User information</h3>


<c:forEach items="${userinfo}" var="user" varStatus = "uStatus"> 
 <form:form name="History" method="POST" 
action="/del" modelAttribute="choosen" commandnName="myhistory">     
    <table width="838" height="140">
        <tr>
  <td width="789">
    <input type = "hidden" name="id" value="${user.getId()}">
    <input type = "hidden" name="username" value="${user.getUsername()}">
    <INPUT type = "hidden" NAME="email" VALUE="${user.getEmail()}">
    <div class="messagee">
    <h2>users: ${user.getId()}</h2>
    <h3>username: ${user.getUsername()}</h3>
    <h4>email: ${user.getEmail()}</h4>
    <h5>password: ${user.getPassword()}</h5>
    </div>
    <BUTTON type="submit" onClick="alertName()" >Delete this</button>
    
          </td>
        </tr>    
           </table>            
 </form:form>
       
    </c:forEach>
<script type="text/javascript">
window.onload = function() {
	alert("You enter user detail page, which will be recorded in admin history");
}
</script> 

</body>
</html>