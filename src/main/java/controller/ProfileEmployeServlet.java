package controller;


import Service.CalculateFamilyAllowanceService;
import Service.EmployeeService;
import model.Auth.Employee;
import model.EmployeFamiliale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/ProfileEmploye")
public class ProfileEmployeServlet extends HttpServlet {
    private CalculateFamilyAllowanceService calculateFamilyAllowanceService = new CalculateFamilyAllowanceService();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Object authenticatedUser = session.getAttribute("Auth");
        if (!(authenticatedUser instanceof Employee)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not authenticated.");
            return;
        }
        long employeeId = ((Employee) authenticatedUser).getId();

        EmployeFamiliale employeFamiliale =  calculateFamilyAllowanceService.getFamilyAllowanceByEmployeeId(employeeId) ;

        System.out.println("employeFamiliale" + employeFamiliale);
        request.setAttribute("employeFamiliale", employeFamiliale);

        request.getRequestDispatcher("EmployeeDashboard/ProfileEmployee.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}

