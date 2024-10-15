<%@ page import="model.OffreEmploi" %>
<%@ page import="model.Auth.RH" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%
    Object authenticatedUser = session.getAttribute("Auth");
    if (!(
            authenticatedUser instanceof RH)
    ) {
        response.sendRedirect("Login");
    }
%>
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
            <c:out value="${not empty offres ? offres.size() : 0}" />
        </button>
    </div>

    <!-- List of Offres -->
    <div class="list">
        <c:if test="${not empty offres}">
            <c:forEach var="offre" items="${offres}">
                <!-- one offre -->
                <div class="OnePost">

                    <div class="profile">
                        <p>Critères Requis: <c:out value="${offre.criteresRequis}" /></p>
                        <h3><c:out value="${offre.description}" /></h3>
                        <p><c:out value="${offre.datePublication}" /></p>
                        <p>Statut: <c:out value="${offre.statut}" /></p>

                    </div>
                </div>
                <!-- one offre -->
            </c:forEach>
        </c:if>
    </div>
</div>
