<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
</head>
<body>
<h3>username login</h3>
<form:form method="POST"
           action="/login" modelAttribute="loginname">
    <table width="240">
        <tr>
            <td width="66"><form:label path="username">UserName</form:label></td>
            <td width="164"><form:input path="username"/></td>
        </tr>
        <tr>
            <td><form:label path="password">
                password</form:label></td>
            <td><form:input path="password"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit"/></td>
        </tr>
    </table>
</form:form>
</body>
</html>