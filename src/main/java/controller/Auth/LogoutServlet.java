package controller.Auth;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Logout")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the current session
        HttpSession session = request.getSession(false);
        if (session != null) {
            // Invalidate the session to log out the user
            session.invalidate();
        }

        // Redirect to the login page or home page
        response.sendRedirect("Login");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // You can implement POST method if needed, usually GET is enough for logout
        doGet(request, response);
    }
}

