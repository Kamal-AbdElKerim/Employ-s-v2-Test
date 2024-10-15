<%@ page import="model.Auth.Admin" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%
  Object authenticatedUser = session.getAttribute("Auth");
  if (!(authenticatedUser instanceof Admin)
  ) {
    response.sendRedirect("Login");
  }
%>
<!DOCTYPE html>
<html>
<head>
  <title>Employee Update</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <link rel="stylesheet" type="text/css" href="assets/css/main.css" />
</head>
<body>

<div class="contianer">
  <div class="signupSection">
    <form action="employees?action=update" method="POST" class="signupForm" name="signupform">
      <h2>Update Employee</h2>

      <c:if test="${not empty error}">
        <h5 style="color: red">${error}.</h5>
      </c:if>

      <input type="hidden" class="inputFields" id="employeeId" name="employeeId" value="${employee.id}" />

      <ul class="noBullet">
        <li>
          <label for="username"></label>
          <input
                  type="text"
                  class="inputFields"
                  id="username"
                  name="name"
                  placeholder="Username"
                  value="${employee.name != null ? employee.name : (name != null ? name : '')}" />
        </li>

        <li>
          <label for="email"></label>
          <input
                  type="email"
                  class="inputFields"
                  id="email"
                  name="email"
                  placeholder="Email"
                  value="${employee.email != null ? employee.email : (email != null ? email : '')}" />
        </li>

        <li>
          <label for="phone"></label>
          <input
                  type="tel"
                  class="inputFields"
                  id="phone"
                  name="telephone"
                  placeholder="Phone"
                  value="${employee.telephone != null ? employee.telephone : (phone != null ? phone : '')}" />
        </li>

        <li>
          <label for="dateNaissance"></label>
          <input
                  type="date"
                  class="inputFields"
                  id="dateNaissance"
                  name="dateNaissance"
                  value="${employee.dateNaissance != null ? employee.dateNaissance : ''}" />
        </li>

        <li>
          <label for="numeroSecuriteSociale"></label>
          <input
                  type="text"
                  class="inputFields"
                  id="numeroSecuriteSociale"
                  name="numeroSecuriteSociale"
                  placeholder="Social Security Number"
                  value="${employee.numeroSecuriteSociale != null ? employee.numeroSecuriteSociale : ''}" />
        </li>

        <li>
          <label for="dateEmbauche"></label>
          <input
                  type="date"
                  class="inputFields"
                  id="dateEmbauche"
                  name="dateEmbauche"
                  value="${employee.dateEmbauche != null ? employee.dateEmbauche : ''}" />
        </li>

        <li>
          <label for="adresse"></label>
          <input
                  type="text"
                  class="inputFields"
                  id="adresse"
                  name="adresse"
                  placeholder="Address"
                  value="${employee.adresse != null ? employee.adresse : ''}" />
        </li>

        <li>
          <label for="department"></label>
          <select id="department" name="department">
            <option value="">Select a Department</option>
            <option value="Ressources Humaines" ${employee.department == 'Ressources Humaines' ? 'selected' : ''}>Ressources Humaines</option>
            <option value="Marketing" ${employee.department == 'Marketing' ? 'selected' : ''}>Marketing</option>
            <option value="Ventes" ${employee.department == 'Ventes' ? 'selected' : ''}>Ventes</option>
          </select>
        </li>

        <li>
          <label for="position"></label>
          <select id="position" name="position">
            <option value="">Select a Position</option>
            <option value="Developer" ${employee.position == 'Developer' ? 'selected' : ''}>Developer</option>
            <option value="Manager" ${employee.position == 'Manager' ? 'selected' : ''}>Manager</option>
            <option value="HR Specialist" ${employee.position == 'HR Specialist' ? 'selected' : ''}>HR Specialist</option>
            <option value="Marketing Specialist" ${employee.position == 'Marketing Specialist' ? 'selected' : ''}>Marketing Specialist</option>
            <option value="Sales Representative" ${employee.position == 'Sales Representative' ? 'selected' : ''}>Sales Representative</option>
          </select>
        </li>

        <li id="center-btn">
          <button type="submit">
            <div class="svg-wrapper-1">
              <div class="svg-wrapper">
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24">
                  <path fill="none" d="M0 0h24v24H0z"></path>
                  <path fill="currentColor" d="M1.946 9.315c-.522-.174-.527-.455.01-.634l19.087-6.362c.529-.176.832.12.684.638l-5.454 19.086c-.15.529-.455.547-.679.045L12 14l6-8-8 6-8.054-2.685z"></path>
                </svg>
              </div>
            </div>
            <span>Update</span>
          </button>
        </li>
      </ul>

      <c:if test="${not empty AddEmployee}">
        <div class="alert_c">
          <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
            ${AddEmployee}.
        </div>
      </c:if>

    </form>
  </div>
  <div class="affichag">
    <h2>Employee Change Log</h2>
    <table border="1">
      <thead>
      <tr>
        <th>Changed At</th>
        <th>Field Changed</th>
        <th>Old Value</th>
        <th>New Value</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="change" items="${changes}">
        <tr>
          <c:set var="formattedDate" value="${fn:substringBefore(change.changedAt, '.')}" />
          <c:set var="formattedDate" value="${fn:replace(formattedDate, 'T', ' ')}" />

          <td>${formattedDate}</td>
          <td>${change.fieldChanged}</td>
          <td>${change.oldValue}</td>
          <td>${change.newValue}</td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
</div>

</body>
</html>
