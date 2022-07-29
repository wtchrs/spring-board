<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
</head>
<body>
<h2>Terms</h2>
<p>Terms content</p>
<form action="register/form" method="post">
    <p><label><input type="checkbox" name="agree" value="true"> Agree.</label></p>
    <input type="submit" value="Next">
</form>
</body>
</html>
