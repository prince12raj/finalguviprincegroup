<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Candidate List</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">
    <h1>Candidate List</h1>
    <a href="candidates?action=new" class="btn btn-primary mb-3">Add New Candidate</a>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Logo URL</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="candidate" items="${candidateList}">
            <tr>
                <td>${candidate.id}</td>
                <td>${candidate.name}</td>
                <td>${candidate.logoUrl}</td>
                <td>
                    <a href="candidates?action=edit&id=${candidate.id}" class="btn btn-warning btn-sm">Edit</a>
                    <a href="candidates?action=delete&id=${candidate.id}" class="btn btn-danger btn-sm">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
    