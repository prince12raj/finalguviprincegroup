<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create New Vote</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container mt-4">
    <h1>Create New Vote</h1>
    <form action="votes" method="post" class="needs-validation" novalidate>
        <input type="hidden" name="action" value="insert">

        <div class="mb-3">
            <label for="userId" class="form-label">User ID</label>
            <input type="number" class="form-control" id="userId" name="userId" required>
            <div class="invalid-feedback">Please provide a valid User ID.</div>
        </div>

        <div class="mb-3">
            <label for="candidateId" class="form-label">Candidate ID</label>
            <input type="number" class="form-control" id="candidateId" name="candidateId" required>
            <div class="invalid-feedback">Please provide a valid Candidate ID.</div>
        </div>

        <button type="submit" class="btn btn-primary">Submit</button>
        <a href="votes" class="btn btn-secondary">Cancel</a>
    </form>
</div>

<script>
    (function () {
        'use strict'

        // Fetch all the forms we want to apply custom Bootstrap validation styles to
        var forms = document.querySelectorAll('.needs-validation')

        // Loop over them and prevent submission
        Array.prototype.slice.call(forms)
            .forEach(function (form) {
                form.addEventListener('submit', function (event) {
                    if (!form.checkValidity()) {
                        event.preventDefault()
                        event.stopPropagation()
                    }

                    form.classList.add('was-validated')
                }, false)
            })
    })()
</script>
</body>
</html>
