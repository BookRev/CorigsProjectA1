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
</head>
<body>
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

<input type="button" name="Submit" οnclick="javascript:history.back(-1);" value="Back">
<input type="button" name="Submit" οnclick="window.open(/favorite)" value="Mark as favorite and Back">
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
