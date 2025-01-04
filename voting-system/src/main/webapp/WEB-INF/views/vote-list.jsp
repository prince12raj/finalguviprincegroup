<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Vote Management</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
        <h2>Vote List</h2>
        <a href="${pageContext.request.contextPath}/vote/new" class="btn btn-success mb-3">Cast New Vote</a>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>User ID</th>
                    <th>Candidate ID</th>
                    <th>Date</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="vote" items="${votes}">
                    <tr>
                        <td><c:out value="${vote.id}" /></td>
                        <td><c:out value="${vote.userId}" /></td>
                        <td><c:out value="${vote.candidateId}" /></td>
                        <td><fmt:formatDate value="${vote.createdAt}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="${pageContext.request.contextPath}/" class="btn btn-secondary">Back to Home</a>
    </div>
</body>
</html>