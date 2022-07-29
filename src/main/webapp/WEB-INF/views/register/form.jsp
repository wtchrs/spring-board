<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
</head>
<body>
<h2>Enter your information</h2>
<form:form action="submit" modelAttribute="registerCommand">
    <p><label>ID<br><form:input path="id"/> <form:errors path="id"/></label></p>
    <p><label>Nickname<br><form:input path="nickname"/> <form:errors path="nickname"/></label></p>
    <p><label>Password<br><form:password path="password"/> <form:errors path="password"/></label></p>
    <p>
        <label>Password Confirm<br>
            <form:password path="passwordConfirm"/> <form:errors path="passwordConfirm"/></label>
    </p>
    <input type="submit" value="Submit">
</form:form>
</body>
</html>
