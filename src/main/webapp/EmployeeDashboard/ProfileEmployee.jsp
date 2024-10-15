<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="model.Auth.Employee" %>
<%
    Object authenticatedUser = session.getAttribute("Auth");
    if (!(authenticatedUser instanceof Employee )
    ) {
        response.sendRedirect("Login");
    }
%>
<html>
<head>
    <title>Profile Employee</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" type="text/css" href="assets/css/navbar.css">

    <style>

.co{
    width: 80%;
    margin: auto;
}

        h1 {
            color: #333; /* Darker color for the heading */
            text-align: center; /* Center the heading */
        }



        .profile-info p {
            font-size: 16px; /* Slightly larger text for readability */
            margin: 10px 0; /* Margin for space between paragraphs */
        }

        strong {
            color: #4CAF50; /* Green color for labels */
        }

    </style>
</head>
<body>
<jsp:include page="../navbar/navbar.jsp" />
<h1>Employee Family Profile</h1>

<c:if test="${not empty employeFamiliale}">
    <div class="co">
    <p><strong>Employee name:</strong> ${employeFamiliale.employee.name}</p>
    <p><strong>Employee Email:</strong> ${employeFamiliale.employee.email}</p>
    <p><strong>Number of Children:</strong> ${employeFamiliale.nombreEnfants}</p>
    <p><strong>Salary:</strong> ${employeFamiliale.salaire } MAD</p>
    <p><strong>Amount of Allocation:</strong> ${employeFamiliale.montantAllocation } MAD</p>
    </div>
</c:if>
</body>
</html>
