<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h2>Login</h2>
<form:form action="login" modelAttribute="login">
    <form:errors/>
    <p><label>ID<br><form:input path="id"/> <form:errors path="id"/></label></p>
    <p><label>Password<br><form:password path="password"/> <form:errors path="password"/></label></p>
    <input type="submit" value="Login">
</form:form>
</body>
</html>
