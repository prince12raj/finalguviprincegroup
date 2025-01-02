<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Candidate Form</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">
    <h1>${candidate != null ? "Edit Candidate" : "Create New Candidate"}</h1>
    <form action="candidates" method="post">
        <input type="hidden" name="action" value="${candidate != null ? 'update' : 'insert'}">
        <input type="hidden" name="id" value="${candidate != null ? candidate.id : ''}">
        <div class="mb-3">
            <label for="name" class="form-label">Name</label>
            <input type="text" class="form-control" id="name" name="name" value="${candidate != null ? candidate.name : ''}" required>
        </div>
        <div class="mb-3">
            <label for="logoUrl" class="form-label">Logo URL</label>
            <input type="url" class="form-control" id="logoUrl" name="logoUrl" value="${candidate != null ? candidate.logoUrl : ''}" required>
        </div>
        <button type="submit" class="btn btn-primary">Save</button>
        <a href="candidates" class="btn btn-secondary">Cancel</a>
    </form>
</div>
</body>
</html>
    