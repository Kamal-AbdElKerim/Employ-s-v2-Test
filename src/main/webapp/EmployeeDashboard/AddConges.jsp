<%@ page import="model.Auth.Employee" %><%
    Object authenticatedUser = session.getAttribute("Auth");
    if (!(authenticatedUser instanceof Employee )
           ) {
        response.sendRedirect("Login");
    }
%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" type="text/css" href="assets/css/navbar.css">

   <style>
       input[type=text], select {
           width: 100%;
           padding: 12px 20px;
           margin: 8px 0;
           display: inline-block;
           border: 1px solid #ccc;
           border-radius: 4px;
           box-sizing: border-box;
       }

       input[type=submit] {
           width: 100%;
           background-color: #4CAF50;
           color: white;
           padding: 14px 20px;
           margin: 8px 0;
           border: none;
           border-radius: 4px;
           cursor: pointer;
       }

       input[type=submit]:hover {
           background-color: #45a049;
       }

       div {
           border-radius: 5px;
           background-color: #f2f2f2;
           padding: 20px;
       }
   </style>
    <title>Add Conge</title>
</head>
<body>
<%
    String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage != null) {
%>
<script>
    alert("<%= errorMessage %>");
</script>
<%
    }
%>
<jsp:include page="../navbar/navbar.jsp" />

<h1>Add Conge</h1>
<div>
<form id="congeForm" action="conges" method="POST" enctype="multipart/form-data">
    <label for="dateDebut">Date Debut:</label>
    <input type="date" id="dateDebut" name="dateDebut" required><br><br>

    <label for="dateFin">Date Fin:</label>
    <input type="date" id="dateFin" name="dateFin" required><br><br>

    <label for="motif">Motif:</label>
    <input type="text" id="motif" name="motif" required><br><br>

    <label for="pdfFile">Upload PDF:</label>
    <input type="file" id="pdfFile" name="pdfFile" accept=".pdf" required><br><br>

    <input type="submit" value="Add Conge">
</form>
</div>



<script>
    // Optional: Add any JavaScript validation if needed
    document.getElementById('congeForm').addEventListener('submit', function(event) {
        const dateDebut = new Date(document.getElementById('dateDebut').value);
        const dateFin = new Date(document.getElementById('dateFin').value);

        if (dateDebut > dateFin) {
            alert('Date Debut must be before Date Fin');
            event.preventDefault(); // Prevent form submission
        }
    });
</script>
</body>
</html>
