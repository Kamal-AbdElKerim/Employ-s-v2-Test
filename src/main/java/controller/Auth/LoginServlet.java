package controller.Auth;


import Service.AdminService;
import Service.CongeService;
import Service.EmployeeService;
import Service.RHService;

import model.Auth.Admin;
import model.Auth.Employee;
import model.Auth.RH;
import model.Conge;
import model.StatutConge;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/Login")

public class LoginServlet extends HttpServlet {

    private final CongeService congeService = new CongeService();
    private final EmployeeService employeeService = new EmployeeService();
    private final AdminService adminService = new AdminService();
    private final RHService rhService = new RHService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("Auth/login.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");



// Check Admins table
        Admin admin = adminService.findAdminByEmail(email);
        Employee employee = employeeService.findEmployeByEmail(email);
        RH rh = rhService.findRHByEmail(email);


        if (admin != null && admin.getPassword().equals(password)) {

                // Successful login, redirect to the appropriate dashboard
                request.getSession().setAttribute("Auth", admin);
                    response.sendRedirect("employees");

        } else if (employee != null && employee.getPassword().equals(password)) {
            request.getSession().setAttribute("Auth", employee);

                response.sendRedirect("conges?action=List");


        } else if (rh != null && rh.getPassword().equals(password)) {
            request.getSession().setAttribute("Auth", rh);

         response.sendRedirect("Offre");

        } else {
            // Invalid credentials, show error message
            request.setAttribute("errorMessage", "Invalid email or password.");
            request.getRequestDispatcher("Auth/login.jsp").forward(request, response);
        }



    }
}



