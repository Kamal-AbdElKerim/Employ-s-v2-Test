package controller;


import Service.CongeService;
import model.Conge;
import Service.CalculateFamilyAllowanceService;
import model.EmployeFamiliale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/Statistiques")
public class StatistiquesServlet extends HttpServlet {
    private final CalculateFamilyAllowanceService calculateFamilyAllowanceService = new CalculateFamilyAllowanceService();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<EmployeFamiliale> AllFamilyAllowances = calculateFamilyAllowanceService.getAllFamilyAllowances();


        request.setAttribute("AllFamilyAllowances", AllFamilyAllowances);

        System.out.println("AllFamilyAllowances" + AllFamilyAllowances);
        request.getRequestDispatcher("RhDashboard/Statistiques.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}

