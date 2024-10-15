<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page import="model.Auth.Employee" %><%
    Object authenticatedUser = session.getAttribute("Auth");
    if (!(authenticatedUser instanceof Employee )
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

<a href="conges?action=form">Add Conges</a>
<table border="1">
    <thead>
    <tr>
        <th>Date Debut</th>
        <th>Date Fin</th>
        <th>Motif</th>
        <th>Statut</th>
        <th>PDF</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="employeeConge" items="${employeeConges}">
        <tr>
            <td><fmt:formatDate value="${employeeConge.dateDebut}" pattern="yyyy-MM-dd" /></td>
            <td><fmt:formatDate value="${employeeConge.dateFin}" pattern="yyyy-MM-dd" /></td>
            <td>${employeeConge.motif}</td>
            <td>${employeeConge.statut}</td>
            <td>
                <c:if test="${not empty employeeConge.pdf}">
                    <a href="downloadPdf?congeId=${employeeConge.congeId}">Download PDF</a>
                </c:if>
                <c:if test="${empty employeeConge.pdf}">
                    No PDF available
                </c:if>
            </td>
        </tr>
    </c:forEach>

    </tbody>
</table>

</body>
</html>
