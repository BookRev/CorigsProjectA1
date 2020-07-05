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
</head>
<body>
<h3>My history</h3>


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
    <BUTTON type="submit">Choose this history</button>
    
          </td>
        </tr>    
           </table>            
 </form:form>
       
    </c:forEach>
   

</body>
</html>