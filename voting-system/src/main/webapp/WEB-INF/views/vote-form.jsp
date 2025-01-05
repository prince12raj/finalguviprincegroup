<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cast Vote</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
        <h2>Cast Vote</h2>
        <form action="${pageContext.request.contextPath}/vote/cast" method="post">
            <div class="form-group">
                <label for="user_id">User ID:</label>
                <input type="number" class="form-control" id="user_id" name="user_id" required>
            </div>
            <div class="form-group">
                <label for="candidate_id">Candidate ID:</label>
                <input type="number" class="form-control" id="candidate_id" name="candidate_id" required>
            </div>
            <button type="submit" class="btn btn-primary">Cast Vote</button>
            <a href="${pageContext.request.contextPath}/vote/list" class="btn btn-secondary">Cancel</a>
        </form>
    </div>
</body>
</html>
