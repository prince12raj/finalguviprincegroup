<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Voting System</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container mt-4">
    <div class="text-center mb-5">
        <h1 class="display-4">Welcome to the Voting System</h1>
        <p class="lead">Manage all aspects of the voting process effortlessly.</p>
    </div>
    <div class="list-group mt-3">
        <a href="users" class="list-group-item list-group-item-action d-flex justify-content-between align-items-center">
            <span>Manage Users</span>
            <span class="badge bg-primary rounded-pill">Users</span>
        </a>
        <a href="candidates" class="list-group-item list-group-item-action d-flex justify-content-between align-items-center">
            <span>Manage Candidates</span>
            <span class="badge bg-success rounded-pill">Candidates</span>
        </a>
        <a href="votes" class="list-group-item list-group-item-action d-flex justify-content-between align-items-center">
            <span>Manage Votes</span>
            <span class="badge bg-danger rounded-pill">Votes</span>
        </a>
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
