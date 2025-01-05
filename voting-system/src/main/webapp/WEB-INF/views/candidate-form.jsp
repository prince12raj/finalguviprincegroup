<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Candidate Form</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
        <h2>Candidate Form</h2>
        <form action="${pageContext.request.contextPath}/candidate/insert" method="post">
            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" class="form-control" id="name" name="name" required>
            </div>
            <div class="form-group">
                <label for="logoUrl">Logo URL:</label>
                <input type="url" class="form-control" id="logoUrl" name="logoUrl" required>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
            <a href="${pageContext.request.contextPath}/candidate/list" class="btn btn-secondary">Cancel</a>
        </form>
    </div>
</body>
</html>