<%@ page import="model.OffreEmploi" %>
<%@ page import="model.Auth.RH" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>

<%
    Object authenticatedUser = session.getAttribute("Auth");
    if (!(
            authenticatedUser instanceof RH )
            ) {
        response.sendRedirect("Login");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>offre List</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" type="text/css" href="assets/css/main.css" />
    <link  rel="stylesheet" type="text/css" href="assets/css/navbar.css">
    <style>
        .affichag{
            width: 100%;
        }
        .OnePost .profile {
            width: 100%;
            display: flex;
            justify-content: space-around;
        }
        .OnePost{
            padding: 44px 7px;
        }
        .OnePost .moreinfo {
            display: block;

        } .OnePost .moreinfo .button-33 {
            margin-bottom: 10px;

        }
    </style>
</head>
<body>
<jsp:include page="../navbar/navbar.jsp" />
<div class="affichag">




    <div class="group">
        <svg viewBox="0 0 24 24" aria-hidden="true" class="search-icon">
            <g>
                <path
                        d="M21.53 20.47l-3.66-3.66C19.195 15.24 20 13.214 20 11c0-4.97-4.03-9-9-9s-9 4.03-9 9 4.03 9 9 9c2.215 0 4.24-.804 5.808-2.13l3.66 3.66c.147.146.34.22.53.22s.385-.073.53-.22c.295-.293.295-.767.002-1.06zM3.5 11c0-4.135 3.365-7.5 7.5-7.5s7.5 3.365 7.5 7.5-3.365 7.5-7.5 7.5-7.5-3.365-7.5-7.5z"
                ></path>
            </g>
        </svg>

        <!-- Count of Offres -->
        <button class="button_Count">
            <svg viewBox="0 0 24 24" width="24" height="24" stroke="currentColor" stroke-width="2" fill="none" stroke-linecap="round" stroke-linejoin="round" class="css-i6dzq1">
                <polygon points="13 2 3 14 12 14 11 22 21 10 12 10 13 2"></polygon>
            </svg>
            <c:out value="${not empty candidature ? candidature.size() : 0}" />
        </button>
    </div>

    <form action="candidature?action=list" method="post">
        <label for="status">Filter by Status:</label>
        <select id="status" name="status">
            <option value="">All</option>
            <option value="ATTENDRE" <c:if test="${selectedStatus == 'ATTENDRE'}">selected</c:if>>Attend</option>
            <option value="ACCEPTE" <c:if test="${selectedStatus == 'ACCEPTE'}">selected</c:if>>Accept</option>
            <option value="REJETE" <c:if test="${selectedStatus == 'REFUSE'}">selected</c:if>>Refuse</option>
            <!-- Add other status options as needed -->
        </select>

        <input type="text" name="skills" placeholder="Enter skill" />

        <button type="submit">Filter</button>
    </form>

    <!-- List of Offres -->
    <div class="list">
        <c:if test="${not empty candidature}">
            <c:forEach var="candidature" items="${candidature}">
                <!-- one offre -->
                <div class="OnePost">

                    <div class="profile">
                       <div>
                           <p>name candidature: <c:out value="${candidature.name}" /></p>
                           <p>Statut: <c:out value="${candidature.statusCandidature}" /></p>
                           <c:forEach var="offre" items="${candidature.offres}">
                              <p>name offre :<c:out value="${offre.description}" /></p>
                           </c:forEach>
                       </div>
                        <div>
                            <h4>skills :</h4>
                            <c:forEach var="skill" items="${candidature.skills}">
                                ${skill}<br/>
                            </c:forEach>
                        </div>




                    </div>
                    <c:if test="${candidature.statusCandidature == 'ATTENDRE'}">
                        <div class="moreinfo">
                            <a class="button-33" href="candidature?id=${candidature.id}&action=accept">Accepter Candidature</a>
                            <a class="button-33 bg_denger" href="candidature?id=${candidature.id}&action=reject">Rejeter Candidatures</a>
                        </div>
                    </c:if>


                </div>
                <!-- one offre -->
            </c:forEach>
        </c:if>
    </div>
</div>
</body>

<script src="assets/js/js.js"></script>

<script
        src="https://kit.fontawesome.com/e9ea9ee727.js"
        crossorigin="anonymous"
></script>
</html>