<%@ page import="model.Auth.Admin" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%
    Object authenticatedUser = session.getAttribute("Auth");
    if (!(authenticatedUser instanceof Admin)) {
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
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        h1 {
            text-align: center;
            margin-bottom: 20px;
        }

        .table-container {
            width: 100%;
            overflow-x: auto;
        }

        table {
            width: 90%;
            margin: 0 auto;
            border-collapse: collapse;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        /* Make the table scrollable on small screens */
        @media (max-width: 768px) {
            table {
                width: 100%;
                display: block;
                overflow-x: auto;
            }

            th, td {
                white-space: nowrap;
            }
        }
    </style>
</head>
<body>
<jsp:include page="../navbar/navbar.jsp" />
<h1>Employee Conges List</h1>

<div class="table-container">
    <table>
        <thead>
        <tr>
            <th>name</th>
            <th>Date Debut</th>
            <th>Date Fin</th>
            <th>Motif</th>
            <th>Statut</th>
            <th>PDF</th>
            <th>t/F</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="employeeConge" items="${employeeConges}">
            <tr>
                <td>${employeeConge.employe.name}</td>
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
                <td>
                    <c:if test="${employeeConge.statut == 'EN_ATTENTE'}">
                        <a href="conges?action=accepteConge&congeId=${employeeConge.congeId}&employeeEmail=${employeeConge.employe.email}">Accepte Congé</a>
                        <a href="conges?action=REJETEConge&congeId=${employeeConge.congeId}&employeeEmail=${employeeConge.employe.email}">REJETE Congé</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
