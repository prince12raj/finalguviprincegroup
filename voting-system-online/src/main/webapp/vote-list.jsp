<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Vote List</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container mt-4">
    <h1 class="mb-3">Vote List</h1>
    <div class="d-flex justify-content-between align-items-center mb-3">
        <a href="votes?action=new" class="btn btn-primary">Add New Vote</a>
        <form class="d-flex" role="search">
            <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success" type="submit">Search</button>
        </form>
    </div>
    <table class="table table-bordered table-striped">
        <thead class="table-dark">
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
                    <div class="btn-group" role="group">
                        <a href="votes?action=delete&id=${vote.id}" class="btn btn-danger btn-sm">Delete</a>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script>
    // Optional: Add interactive elements or functionality here if needed
</script>
</body>
</html>
