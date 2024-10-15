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
import java.io.IOException;

@WebServlet("/calculateFamilyAllowance")
public class FamilyAllowanceServlet extends HttpServlet {
    private CalculateFamilyAllowanceService calculateFamilyAllowanceService = new CalculateFamilyAllowanceService();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));

        request.setAttribute("id", id);

        EmployeFamiliale employeFamiliale =  calculateFamilyAllowanceService.getFamilyAllowanceByEmployeeId(id);

        request.setAttribute("employeFamiliale", employeFamiliale);
        System.out.println("employeFamiliale" + employeFamiliale);

        request.getRequestDispatcher("AdminDashboard/familyAllowanceForm.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long employeeId = Long.valueOf(request.getParameter("id"));

        EmployeeService  employeeService = new EmployeeService();
        Employee existingEmployee = employeeService.getEmployeeById(employeeId);

        if (employeeId != null) {
            String s = request.getParameter("salary") ;
            int numberOfChildren = Integer.parseInt(request.getParameter("numberOfChildren"));

            if (s != null && !s.isEmpty()) {

            double salary = Double.parseDouble(s);
            // Calculate family allowance
            double allowance = calculateFamilyAllowanceService.calculateAllowance(numberOfChildren, salary);

            // Set attributes for the JSP
            request.setAttribute("employeeId", employeeId);




            EmployeFamiliale employeFamiliale = new EmployeFamiliale( numberOfChildren, salary, allowance, existingEmployee);

            System.out.println(employeFamiliale);


            calculateFamilyAllowanceService.calculateFamilyAllowance(employeFamiliale);

            }else {
                Long employeFamilialeId = Long.valueOf(request.getParameter("ID"));


                EmployeFamiliale getFamilyAllowancesById =  calculateFamilyAllowanceService.getFamilyAllowancesById(employeFamilialeId);

                double allowance = calculateFamilyAllowanceService.calculateAllowance(numberOfChildren + getFamilyAllowancesById.getNombreEnfants(), getFamilyAllowancesById.getSalaire());

                EmployeFamiliale employeFamiliale = new EmployeFamiliale( numberOfChildren + getFamilyAllowancesById.getNombreEnfants(), getFamilyAllowancesById.getSalaire(), allowance, existingEmployee);

                boolean updateemployeFamiliale =  calculateFamilyAllowanceService.updateFamilyAllowance(employeFamilialeId , employeFamiliale);


            }

            response.sendRedirect("employees");


        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID not found.");
        }


    }



}

