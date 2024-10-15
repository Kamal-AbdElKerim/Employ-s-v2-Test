<%@ page import="model.Auth.Admin" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    Object authenticatedUser = session.getAttribute("Auth");
    if (!(authenticatedUser instanceof Admin)
    ) {
        response.sendRedirect("Login");
    }
%>
<html>
<head>
    <title>Employee Conges List</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="assets/css/navbar.css">
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        table {
            width: 90%;
            margin: auto;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
<jsp:include page="../navbar/navbar.jsp" />
<h1>Employee Conges List</h1>

<table border="1">
    <thead>
    <tr>
        <th>Name</th>
        <th>Date Debut</th>
        <th>Date Fin</th>
        <th>Days</th> <!-- Added column for days -->
        <th>soldeConges</th> <!-- Added column for days -->

    </tr>
    </thead>
    <tbody>
    <c:forEach var="employeeConge" items="${approvedConges}">
        <tr>
            <td>${employeeConge.employe.name}</td>
            <td><fmt:formatDate value="${employeeConge.dateDebut}" pattern="yyyy-MM-dd" /></td>
            <td><fmt:formatDate value="${employeeConge.dateFin}" pattern="yyyy-MM-dd" /></td>
            <td>${employeeConge.diffInDays}</td>
            <td>${employeeConge.employe.soldeConges}</td>



        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
