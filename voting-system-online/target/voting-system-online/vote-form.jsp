<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>New Vote</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">
    <h1>Create New Vote</h1>
    <form action="votes" method="post">
        <input type="hidden" name="action" value="insert">
        <div class="mb-3">
            <label for="userId" class="form-label">User ID</label>
            <input type="number" class="form-control" id="userId" name="userId" required>
        </div>
        <div class="mb-3">
            <label for="candidateId" class="form-label">Candidate ID</label>
            <input type="number" class="form-control" id="candidateId" name="candidateId" required>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
        <a href="votes" class="btn btn-secondary">Cancel</a>
    </form>
</div>
</body>
</html>
    