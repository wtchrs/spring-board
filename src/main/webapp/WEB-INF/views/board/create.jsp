<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create Board</title>
</head>
<body>
<h2>Create new board</h2>
<form:form action="post" modelAttribute="createBoard">
    <p><label>Board Title<br><form:input path="title"/> <form:errors path="title"/></label></p>
    <input type="submit">
</form:form>
</body>
</html>
