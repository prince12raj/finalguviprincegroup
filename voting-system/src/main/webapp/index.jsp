<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Voting System</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
        <h1>Welcome to Voting System</h1>
        <div class="row mt-4">
            <div class="col-md-4">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">User Management</h5>
                        <p class="card-text">Manage users of the voting system.</p>
                        <a href="${pageContext.request.contextPath}/user/list" class="btn btn-primary">Go to Users</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Candidate Management</h5>
                        <p class="card-text">Manage election candidates.</p>
                        <a href="${pageContext.request.contextPath}/candidate/list" class="btn btn-primary">Go to Candidates</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Vote Management</h5>
                        <p class="card-text">View and manage votes.</p>
                        <a href="${pageContext.request.contextPath}/vote/list" class="btn btn-primary">Go to Votes</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>