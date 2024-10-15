<%@ page import="model.Auth.RH" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    Object authenticatedUser = session.getAttribute("Auth");
    if (!(
            authenticatedUser instanceof RH)
    ) {
        response.sendRedirect("Login");
    }
%>
<html>
<head>
    <title>Family Allowances Statistics</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link  rel="stylesheet" type="text/css" href="assets/css/navbar.css">

    <style>


        h1 {
            text-align: center;
            color: #4CAF50;
            margin-bottom: 20px;
        }

        table {
            border-collapse: collapse;
            width: 80%;
            margin: 20px auto;
            background-color: white;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
        }

        th, td {
            text-align: left;
            padding: 12px 20px;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #4CAF50;
            color: white;
            font-weight: bold;
        }

        td {
            background-color: #fafafa;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #ddd;
        }

        td:last-child {
            font-weight: bold;
            color: #333;
        }

        @media (max-width: 768px) {
            table {
                width: 100%;
            }

            th, td {
                padding: 10px;
            }
        }
    </style>
</head>
<body>
<jsp:include page="../navbar/navbar.jsp" />

<h1>Family Allowances Statistics</h1>

<table border="1">
    <thead>
    <tr>
        <th>Employee Name</th>
        <th>Number of Children</th>
        <th>Salary</th>
        <th>Allowance Amount</th>
        <th>Total Salary par mois</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="allowance" items="${AllFamilyAllowances}">
        <tr>
            <td>${allowance.employee.name}</td>
            <td>${allowance.nombreEnfants} Childrens</td>
            <td>${allowance.salaire} MAD</td>
            <td>${allowance.montantAllocation} MAD</td>
            <td>${allowance.montantAllocation + allowance.salaire } MAD</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
