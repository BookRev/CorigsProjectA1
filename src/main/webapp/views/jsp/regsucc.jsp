<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
body {
	font-size: 24px;
}
body h3 {
	font-size: 36px;
}
body h4 {
	font-size: 36px;
}
body h5 {
	font-size: 36px;
}
</style>
</head>
<body>
         <div class="starter-template">
		   <h1>Register Success</h1>
         </div>
			<h2>Your Username: ${username}</h2>
			<h3>Your Email: ${email}</h3>
<h5>Your Password: ${password}</h5>
           

<form:form method="POST"
           action="/home" modelAttribute="noused">
  <input type="submit" value="Home Page"/>
</form:form>


</body>
</html>