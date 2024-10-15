<%@ page import="model.Auth.Employee" %>
<%@ page import="model.Auth.Admin" %>
<%@ page import="model.Auth.RH" %>

<%
    Object authenticatedUser = session.getAttribute("Auth");
 //   if (!(authenticatedUser instanceof Employee ||
 //           authenticatedUser instanceof RH ||
 //           authenticatedUser instanceof Admin)) {
 //       response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not authenticated.");
 //       return;
 //   }
%>

<div class="topnav" id="myTopnav">
    <% if (authenticatedUser instanceof Employee) { %>
    <a href="conges?action=List" class="active">List Conges</a>
    <a href="ProfileEmploye">Profile</a>
    <% } %>
    <% if (authenticatedUser instanceof Admin) { %>
    <a href="employees" class="active">List employees</a>
    <a href="conges?action=ListParAdmin" class="active">List Conges par admin</a>
    <a href="Reporting" class="active">Report</a>


    <% } %>
    <% if (authenticatedUser instanceof RH ) { %>
    <a href="candidature?action=list" class="active">List Regiser Offre</a>
    <a href="Offre" class="active">List Offre</a>
    <a href="Statistiques" class="active">Statistiques</a>

    <% } %>
    <% if (authenticatedUser != null) { %>
    <a href="Logout">Logout</a>
    <% } else { %>
    <a href="Login">Login</a>
    <% } %>
    <a href="javascript:void(0);" class="icon" onclick="myFunction()">
        <i class="fa fa-bars"></i>
    </a>
</div>
<% if (authenticatedUser instanceof Admin) { %>
<jsp:include page="../notification/Notification.jsp" />


<% } %>


<script>
    function myFunction() {
        var x = document.getElementById("myTopnav");
        if (x.className === "topnav") {
            x.className += " responsive";
        } else {
            x.className = "topnav";
        }
    }
</script>
