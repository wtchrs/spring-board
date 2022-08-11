<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Board List</title>
</head>
<body>
<h2>Board List</h2>
<c:if test="${! empty boards}">
    <table>
        <tr>
            <th>No</th>
            <th>Title</th>
            <th>Open Date</th>
        </tr>
        <c:forEach var="board" items="${boards}">
            <tr>
                <th>${board.no}</th>
                <th><a href="<c:url value="/board/${board.title}"/>">${board.title}</a></th>
                <th><tf:formatDateTime value="${board.openDate}" pattern="yyyy-MM-dd"/></th>
            </tr>
        </c:forEach>
    </table>
</c:if>
<p><a href="<c:url value="/board/create"/>">[Create new board]</a></p>
</body>
</html>
