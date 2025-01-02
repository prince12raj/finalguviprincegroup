<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Vote List</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">
    <h1>Vote List</h1>
    <a href="votes?action=new" class="btn btn-primary mb-3">Add New Vote</a>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>ID</th>
                <th>User ID</th>
                <th>Candidate ID</th>
                <th>Created At</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="vote" items="${voteList}">
            <tr>
                <td>${vote.id}</td>
                <td>${vote.userId}</td>
                <td>${vote.candidateId}</td>
                <td>${vote.createdAt}</td>
                <td>
                    <a href="votes?action=delete&id=${vote.id}" class="btn btn-danger btn-sm">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
    