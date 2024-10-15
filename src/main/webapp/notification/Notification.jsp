

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="notification">
    <input type="checkbox" name="uro" id="uro">
    <input type="checkbox" name="btn" id="btn" >
    <label for="btn">
        <span class="counter">${notifications.size()}</span>
    </label>
    <div class="panel">
        <ul>
            <c:forEach var="notification" items="${notifications}">

                <li class="unread"><img src="https://i.pravatar.cc/40?img=11"> ${notification.sujet} <a href="#"> ${notification.message}</a></li>

            </c:forEach>

        </ul>
    </div>
</div>