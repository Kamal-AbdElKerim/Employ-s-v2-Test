package controller;

import Service.CalculateFamilyAllowanceService;
import Service.EmployeeService;
import Service.NotificationService;
import com.google.gson.Gson;
import model.Auth.Employee;
import model.Auth.EmployeeChange;
import model.Notification;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet("/employees")
public class EmployeeServlet extends HttpServlet {
    private EmployeeService employeeService;


    @Override
    public void init() throws ServletException {
        employeeService = new EmployeeService();
    }

    // Handle GET requests for listing all employees
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Employee> employees = employeeService.getAllEmployees();
        NotificationService notificationService = new NotificationService();
        List<Notification> notifications = notificationService.findAllNotifications();


        request.setAttribute("notifications", notifications);
        request.setAttribute("employees", employees);
        request.getRequestDispatcher("/Employee/index.jsp").forward(request, response);
    }

    // Handle POST requests for adding/updating/deleting employees
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "add":
                addEmployee(request, response);
                break;
            case "edit":
                editEmployee(request, response);
                break;
            case "update":
                updateEmployee(request, response);
                break;
            case "delete":
                deleteEmployee(request, response);
                break;

            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    private void addEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Date dateNaissance = Date.valueOf(request.getParameter("dateNaissance"));
        String numeroSecuriteSociale = request.getParameter("numeroSecuriteSociale");
        Date dateEmbauche = Date.valueOf(request.getParameter("dateEmbauche"));
        String adresse = request.getParameter("adresse");
        String telephone = request.getParameter("phone");
        String department = request.getParameter("department");
        String position = request.getParameter("position");
        LocalDateTime createdAt = LocalDateTime.now();

        // Create a new Employee object from the request parameters
        Employee employee = new Employee(name, email, password, dateNaissance, numeroSecuriteSociale,
                dateEmbauche, adresse, telephone, department, position, createdAt);

        employeeService.addEmployee(employee);
        request.setAttribute("message", "L'employé " + name + " a été ajouté avec succès !");
        request.setAttribute("color", "#2E8B57");
        doGet(request, response);
    }

    private void editEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      Long employeeId = Long.valueOf(request.getParameter("employeeId"));

      List<EmployeeChange> changes = employeeService.getEmployeeChangeHistory(employeeId);
        System.out.println("changessssss" + changes);



        Employee existingEmployee = employeeService.getEmployeeById(employeeId);
        if (existingEmployee != null) {
            request.setAttribute("employee", existingEmployee);
            request.setAttribute("changes", changes);
            request.getRequestDispatcher("/Employee/updateEmployee.jsp").forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Employee not found");
        }
    }

    private void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long employeeId = Long.valueOf(request.getParameter("employeeId"));


            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            Date dateNaissance = Date.valueOf(request.getParameter("dateNaissance"));
            String numeroSecuriteSociale = request.getParameter("numeroSecuriteSociale");
            Date dateEmbauche = Date.valueOf(request.getParameter("dateEmbauche"));
            String adresse = request.getParameter("adresse");
            String telephone = request.getParameter("telephone");
            String department = request.getParameter("department");
            String position = request.getParameter("position");

          employeeService.updateEmployee(employeeId , new Employee(name,email,password,dateNaissance ,numeroSecuriteSociale ,dateEmbauche,adresse,telephone,department,position,LocalDateTime.now()));
        request.setAttribute("message", "employee " + name + " updated successfully");
        request.setAttribute("color", "#2E8B57");
        doGet(request, response);    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long employeeId = Long.valueOf(request.getParameter("employeeId"));
        employeeService.deleteEmployee(employeeId);
        request.setAttribute("message", "Employee deleted successfully!");
        request.setAttribute("color", "#333f27");
        doGet(request, response);
    }

}
