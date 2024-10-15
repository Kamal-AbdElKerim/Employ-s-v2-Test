<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Add Candidature</title>
    <link rel="stylesheet" type="text/css" href="assets/Scss/formOffre.scss" />
</head>
<body>
<div class="container">
    <div class="text">
        Contact Us Form
    </div>
    <c:if test="${not empty errorMessage}">
        <div class="error-message" style="color: red; font-weight: bold;">
                ${errorMessage}
        </div>
    </c:if>
    <form action="candidature?action=add" method="POST">
        <input type="text" id="id" name="id" value="${param.id}" style="display: none" />

        <div class="form-row">
            <div class="input-data">
                <input type="text" name="fullName" required>
                <div class="underline"></div>
                <label>Full Name</label>
            </div>
            <div class="input-data">
                <input type="text" name="email" required>
                <div class="underline"></div>
                <label>Email Address</label>
            </div>
        </div>

        <label class="container2">JAVA
            <input type="checkbox" name="skills" value="Java" checked="checked">
            <span class="checkmark"></span>
        </label>
        <label class="container2">PHP
            <input type="checkbox" name="skills" value="PHP">
            <span class="checkmark"></span>
        </label>
        <label class="container2">LARAVEL
            <input type="checkbox" name="skills" value="Laravel">
            <span class="checkmark"></span>
        </label>
        <label class="container2">HTML
            <input type="checkbox" name="skills" value="HTML">
            <span class="checkmark"></span>
        </label>

        <div class="form-row">
            <div class="input-data textarea">
                <!-- Add any additional fields here -->
            </div>
        </div>

        <div class="form-row submit-btn">
            <div class="input-data">
                <div class="inner"></div>
                <input type="submit" value="Submit">
            </div>
        </div>
    </form>
</div>
</body>
</html>
