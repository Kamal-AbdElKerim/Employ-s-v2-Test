<%@ page import="model.Auth.Employee" %>
<%@ page import="model.Auth.RH" %>
<%@ page import="model.Auth.Admin" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
  Object authenticatedUser = session.getAttribute("Auth");
    if (!(
            authenticatedUser instanceof RH
            )) {
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
    .OnePost .profile {
      width: 100%;
    }
    .OnePost{
      padding: 44px 7px;
    }
  </style>
</head>
<body>
<jsp:include page="../navbar/navbar.jsp" />
<div class="contianer">
  <div class="signupSection">
    <form action="Offre" method="POST" class="signupForm" name="signupform">
      <h2>Ajouter un nouvel offre</h2>

      <!-- Display error message -->
      <c:if test="${not empty error}">
        <h5 style="color: red">${error}.</h5>
      </c:if>

      <ul class="noBullet">
        <li>
          <label for="criteresRequis"></label>
          <input
                  type="text"
                  class="inputFields"
                  id="criteresRequis"
                  name="criteresRequis"
                  placeholder="criteresRequis"
                  value="${criteresRequis != null ? criteresRequis : ''}" />
        </li>

        <li>
          <label for="description"></label>
          <input
                  type="text"
                  class="inputFields"
                  id="description"
                  name="description"
                  placeholder="description"
                  value="${description != null ? description : ''}" />
        </li>


        <li>
          <label for="datePublication"></label>
          <input
                  type="date"
                  class="inputFields"
                  id="datePublication"
                  name="datePublication"
                  placeholder="date Publication"
                  value="${datePublication != null ? datePublication : ''}" />
        </li>

        <li id="center-btn">
          <button type="submit">
            <div class="svg-wrapper-1">
              <div class="svg-wrapper">
                <svg
                        xmlns="http://www.w3.org/2000/svg"
                        viewBox="0 0 24 24"
                        width="24"
                        height="24">
                  <path fill="none" d="M0 0h24v24H0z"></path>
                  <path
                          fill="currentColor"
                          d="M1.946 9.315c-.522-.174-.527-.455.01-.634l19.087-6.362c.529-.176.832.12.684.638l-5.454 19.086c-.15.529-.455.547-.679.045L12 14l6-8-8 6-8.054-2.685z">
                  </path>
                </svg>
              </div>
            </div>
            <span>Ajouter</span>
          </button>
        </li>
      </ul>

      <!-- Include alert messages -->
      <%@ include file="../alert/alert.jsp" %>
    </form>
  </div>




    <jsp:include page="Listoffre.jsp" />

  </body>

<script src="assets/js/js.js"></script>

  <script
          src="https://kit.fontawesome.com/e9ea9ee727.js"
          crossorigin="anonymous"
  ></script>
</html>
