<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>${title}</title>
</head>
<body>
<h2>Post List : ${title}</h2>
<c:if test="${! empty posts}">
    <table>
        <tr>
            <th>No</th>
            <th>Title</th>
            <th>Author</th>
            <th>Date</th>
        </tr>
        <c:forEach var="post" items="${posts}" varStatus="status">
            <tr>
                <th>${post.no}</th>
                <th><a href="<c:url value="/post/${post.no}"/>">${post.title}</a></th>
                <th><a href="<c:url value="/user/${authors[status.index].id}"/>">${authors[status.index].nickname}</a></th>
                <th><tf:formatDateTime value="${post.date}" pattern="yyyy-MM-dd HH:mm"/></th>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
