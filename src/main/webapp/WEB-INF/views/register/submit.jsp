<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
</head>
<body>
<h2>Register finished</h2>
<p>${registerCommand.nickname}, your register request successfully finished!</p>
<p><a href="<c:url value="/main"/>">[Main page]</a> <a href="<c:url value="/login"/>">[Login]</a></p>
</body>
</html>
