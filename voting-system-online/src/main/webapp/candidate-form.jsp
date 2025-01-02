<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Candidate Form</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container mt-4">
    <h1>${candidate != null ? "Edit Candidate" : "Create New Candidate"}</h1>
    <form action="candidates" method="post" class="needs-validation" novalidate>
        <input type="hidden" name="action" value="${candidate != null ? 'update' : 'insert'}">
        <input type="hidden" name="id" value="${candidate != null ? candidate.id : ''}">
        
        <div class="mb-3">
            <label for="name" class="form-label">Name</label>
            <input type="text" class="form-control" id="name" name="name" value="${candidate != null ? candidate.name : ''}" required>
            <div class="invalid-feedback">Please provide a valid name.</div>
        </div>

        <div class="mb-3">
            <label for="logoUrl" class="form-label">Logo URL</label>
            <input type="url" class="form-control" id="logoUrl" name="logoUrl" value="${candidate != null ? candidate.logoUrl : ''}" required>
            <div class="invalid-feedback">Please provide a valid URL.</div>
        </div>

        <button type="submit" class="btn btn-primary">Save</button>
        <a href="candidates" class="btn btn-secondary">Cancel</a>
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
