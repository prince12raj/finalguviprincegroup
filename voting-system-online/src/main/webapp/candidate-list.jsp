<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Candidate List</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container mt-4">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h1>Candidate List</h1>
        <a href="candidates?action=new" class="btn btn-primary">Add New Candidate</a>
    </div>
    
    <div class="table-responsive">
        <table class="table table-striped table-hover table-bordered align-middle">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Logo</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="candidate" items="${candidateList}">
                <tr>
                    <td>${candidate.id}</td>
                    <td>${candidate.name}</td>
                    <td>
                        <img src="${candidate.logoUrl}" alt="${candidate.name}" class="img-thumbnail" style="max-width: 100px; height: auto;">
                    </td>
                    <td>
                        <div class="btn-group" role="group" aria-label="Actions">
                            <a href="candidates?action=edit&id=${candidate.id}" class="btn btn-warning btn-sm">Edit</a>
                            <a href="candidates?action=delete&id=${candidate.id}" class="btn btn-danger btn-sm">Delete</a>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        const tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
        const tooltipList = tooltipTriggerList.map(function(tooltipTriggerEl) {
            return new bootstrap.Tooltip(tooltipTriggerEl);
        });
    });
</script>
</body>
</html>
