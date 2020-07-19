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
					<li class="active"><a href="/search">Search books</a></li>
					<li class="active"><a href="/history">My history</a></li>
					<li class="active"><a href="/favorite">My Favorites</a></li>
				</ul>
			</div>
		</div>
	</nav>
<h3>My ${title}</h3>


<c:forEach items="${myhistory}" var="hist" varStatus = "uStatus"> 
 <form:form name="History" method="POST" 
action="/history1" modelAttribute="choosen" commandnName="myhistory">     
    <table width="838" height="140">
        <tr>
  <td width="789">
  <input type="image"  src="${hist.getBookpic()}" height  = "200"  >
    <input type = "hidden" name="isbn" value="${hist.getIsbn()}">
    <input type = "hidden" name="myhistory" value="${hist.getBookname()}">
    <INPUT type = "hidden" NAME="author" VALUE="${hist.getAuthor()}">
    <div class="messagee">
    <h2>${hist.getBookname()}</h2>
    <h3>${hist.getAuthor()}</h3>
    <h5>ISBN-13: ${hist.getIsbn()}</h5>
    </div>
    <BUTTON type="submit">Choose this</button>
    
          </td>
        </tr>    
           </table>            
 </form:form>
     <form:form name="History" method="POST" 
action="/delbook" modelAttribute="delbook" commandnName="myhistory">  
      <input type = "hidden" name="isbn" value="${hist.getIsbn()}">
    <input type = "hidden" name="myhistory" value="${hist.getBookname()}">
    <INPUT type = "hidden" NAME="author" VALUE="${hist.getAuthor()}">
    <INPUT type = "hidden" NAME="favorhist" VALUE="${title}">
<BUTTON type="submit" onClick="alertName()">Delete this</button>
</form:form>   
    </c:forEach>
   
 <script> 
   function alertName(){
alert("Delete success");}
</script> 

</body>
</html>