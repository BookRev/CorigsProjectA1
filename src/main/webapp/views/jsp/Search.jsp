<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
</head>
<body>
<h3>search</h3>
<form:form method="POST"
           action="/search" modelAttribute="search">
    <table width="240">
        <tr>
            <td width="66"><form:label path="isbn">Isbn</form:label></td>
            <td width="164"><form:input path="isbn"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit"/></td>
        </tr>
    </table>
</form:form>
</body>
</html>