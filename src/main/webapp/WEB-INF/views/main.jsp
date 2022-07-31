<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Spring Board Example</title>
</head>
<body>
<c:if test="${empty authInfo}">
    <p>
        <a href="<c:url value="/register"/>">[Register]</a>
        <a href="<c:url value="/login"/>">[Log in]</a>
    </p>
</c:if>
<c:if test="${!empty authInfo}">
    <p>${authInfo.nickname} <a href="<c:url value="/logout"/>">[Log out]</a></p>
</c:if>
<h2>Welcome to Spring Board Example!</h2>
</body>
</html>
