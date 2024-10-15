<!-- alert.jsp -->
<%
    String message = (String) request.getAttribute("message");
    String color = (String) request.getAttribute("color");
    if (message != null && color != null) {
%>
<div class="alert_c" style="background-color: <%= color %>;">
    <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
    <%= message %>
</div>
<%
    }
%>
