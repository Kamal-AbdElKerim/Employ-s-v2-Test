package controller;


import Service.CalculateFamilyAllowanceService;
import Service.CongeService;
import Service.NotificationService;
import model.Auth.Employee;
import model.Conge;
import model.EmployeFamiliale;
import model.Notification;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/Reporting")
public class ReportingServlet extends HttpServlet {
    private final CongeService congeService = new CongeService();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Conge> approvedConges = congeService.getApprovedConges();

        // Debugging output to ensure conges are fetched correctly
        System.out.println(approvedConges);

        // Set the approvedConges list as a request attribute
        request.setAttribute("approvedConges", approvedConges);

        NotificationService notificationService = new NotificationService();
        List<Notification> notifications = notificationService.findAllNotifications();


        request.setAttribute("notifications", notifications);

        // Forward the request to the JSP page
        request.getRequestDispatcher("AdminDashboard/Reporting.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}

