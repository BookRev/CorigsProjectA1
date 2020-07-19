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
<h3>Finding: ${founded} books</h3>


<c:forEach items="${ret}" var="thisbook" varStatus = "uStatus"> 
 <form:form name="Searchname" method="POST" 
action="/search" modelAttribute="search" commandnName="searchret">     
    <table width="838" height="140">
        <tr>
  <td width="789">
  <input type="image"  src="${thisbook.getBookpic()}" height  = "200"  >
    <input type = "hidden" name="sisbn" value="${thisbook.getIsbn()}">
    <input type = "hidden" name="bookname" value="${thisbook.getBookname()}">
    <div class="messagee">
    <h2>${thisbook.getBookname()}</h2>
    <h5>ISBN-13: ${thisbook.getIsbn()}</h5>
    </div>
    <BUTTON type="submit">Choose this</button>
          </td>
        </tr>    
           </table>            
 </form:form>
       
    </c:forEach>
    
    <form:form method="POST"
           action="/search4" modelAttribute="page0">
           <h5>Pages: ${page} / ${allpage}</h5>
           <input type = "hidden" name="spageno" value="${page}">
           <input type = "hidden" name="title" value="${searchtitle}">
           <input type = "hidden" name="type" value="previous">
           <input type = "hidden" name="total" value="${allpage}">
           <BUTTON type="submit">Previous Page</button>
</form:form>

<form:form method="POST"
           action="/search4" modelAttribute="page1">
           <input type = "hidden" name="spageno" value="${page}">
           <input type = "hidden" name="title" value="${searchtitle}">
           <input type = "hidden" name="type" value="next">
           <input type = "hidden" name="total" value="${allpage}">
           <BUTTON type="submit">Next Page</button>
</form:form>

<form:form method="POST"
           action="/search4" modelAttribute="page2">
           <td width="66"><form:label path="spageno">Page</form:label></td>
            <td width="164"><form:input path="spageno"/></td>
           <input type = "hidden" name="title" value="${searchtitle}">
           <input type = "hidden" name="type" value="jump">
           <input type = "hidden" name="total" value="${allpage}">
           <BUTTON type="submit">Jump to this page</button>
</form:form>

</body>
</html>