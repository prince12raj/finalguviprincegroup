<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Candidate Management</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
        <h2>Candidate List</h2>
        <a href="${pageContext.request.contextPath}/candidate/new" class="btn btn-success mb-3">Add New Candidate</a>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Logo</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="candidate" items="${candidates}">
                    <tr>
                        <td><c:out value="${candidate.id}" /></td>
                        <td><c:out value="${candidate.name}" /></td>
                        <td><img src="${candidate.logoUrl}" alt="Candidate Logo" height="50"></td>
                        <td>
                            <a href="${pageContext.request.contextPath}/candidate/edit?id=<c:out value='${candidate.id}' />" 
                               class="btn btn-primary btn-sm">Edit</a>
                            <a href="${pageContext.request.contextPath}/candidate/delete?id=<c:out value='${candidate.id}' />" 
                               class="btn btn-danger btn-sm">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="${pageContext.request.contextPath}/" class="btn btn-secondary">Back to Home</a>
    </div>
</body>
</html>