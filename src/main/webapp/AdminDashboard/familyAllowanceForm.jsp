<%@ page import="model.Auth.Admin" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    Object authenticatedUser = session.getAttribute("Auth");
    if (!(authenticatedUser instanceof Admin)
    ) {
        response.sendRedirect("Login");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Calculate Family Allowance</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }

        h1 {
            color: #333;
            text-align: center;
        }

        .info-table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }

        .info-table th, .info-table td {
            padding: 10px;
            text-align: left;
            border: 1px solid #ddd;
        }

        .info-table th {
            background-color: #4CAF50;
            color: white;
        }

        form {
            background-color: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            margin: auto;
        }

        form label {
            display: block;
            margin: 10px 0 5px;
        }

        form input[type="number"], form input[type="submit"] {
            width: 100%;
            padding: 8px;
            margin: 5px 0 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        form input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }

        form input[type="submit"]:hover {
            background-color: #45a049;
        }

    </style>
</head>
<body>
<h1>Family Allowance Calculation</h1>
<c:if test="${employeFamiliale != null}">
    <table class="info-table">
        <tr>
            <th>Allocation ID</th>
            <td>${employeFamiliale.allocationId}</td>
        </tr>
        <tr>
            <th>Nombre d'enfants</th>
            <td>${employeFamiliale.nombreEnfants}</td>
        </tr>
        <tr>
            <th>Salaire</th>
            <td>${employeFamiliale.salaire}</td>
        </tr>
        <tr>
            <th>Montant Allocation</th>
            <td>${employeFamiliale.montantAllocation}</td>
        </tr>
        <tr>
            <th>Employee Email</th>
            <td>${employeFamiliale.employee.email}</td>
        </tr>
    </table>
</c:if>

<!-- Display the form only if the number of children is less than 6 -->
<c:if test="${employeFamiliale.nombreEnfants < 6 || employeFamiliale == null}">
    <form action="calculateFamilyAllowance" method="post">
        <!-- Hidden field to pass the employee ID -->
        <input type="hidden" name="id" value="${id}">

        <label for="numberOfChildren">Number of Dependent Children:</label>
        <input type="number" id="numberOfChildren" name="numberOfChildren" min="1" max="${6 - employeFamiliale.nombreEnfants}" required><br><br>

        <c:if test="${employeFamiliale == null}">
            <label for="salary">Salary (in DH):</label>
            <input type="number" id="salary" name="salary" required><br><br>
        </c:if>

        <c:if test="${employeFamiliale != null}">
            <input type="hidden" value="${employeFamiliale.allocationId}" name="ID"><br><br>
        </c:if>

        <input type="submit" value="Calculate Allowance">
    </form>
</c:if>
</body>
</html>
