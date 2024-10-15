package controller;

import Service.EmployeeService;
import Service.NotificationService;
import email.Gmail;
import model.Conge;
import model.Auth.Employee;
import Service.CongeService;
import model.Notification;
import model.StatutConge;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@WebServlet("/conges")
@MultipartConfig
public class CongeServlet extends HttpServlet {

    private final CongeService congeService = new CongeService();
    private final EmployeeService employeeService = new EmployeeService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        if (action == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing action parameter.");
            return;
        }

        switch (action) {
            case "List":
                handleListAction(request, response, session);
                break;
            case "ListParAdmin":
                handleListByAdminAction(request, response);
                break;
            case "accepteConge":
                handleAcceptCongeAction(request, response);
                break;
            case "REJETEConge":
                handleRejectCongeAction(request, response);
                break;
            case "form":
                request.getRequestDispatcher("EmployeeDashboard/AddConges.jsp").forward(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unsupported action: " + action);
                break;
        }
    }




    private void handleListAction(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
        Object authenticatedUser = session.getAttribute("Auth");
        if (!(authenticatedUser instanceof Employee)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not authenticated.");
            return;
        }
        long employeeId = ((Employee) authenticatedUser).getId();
        int currentYear = LocalDate.now().getYear();

        List<Employee> employees = employeeService.getAllEmployees();

        for (Employee employee : employees) {
            LocalDateTime createdAt = employee.getCreatedAt();

            Instant instant = createdAt.atZone(ZoneId.systemDefault()).toInstant();

            LocalDate createdDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
            int employeeCreatedYear = createdDate.getYear();
            System.out.println(employeeCreatedYear );
            System.out.println(currentYear );
            if (employeeCreatedYear < currentYear) {
                System.out.println(employeeCreatedYear );
                System.out.println(currentYear );
                employee.setSoldeConges(30L);
                employee.setCreatedAt(LocalDateTime.now());
                employeeService.updateEmployee(employee.getId(),employee);
            }
        }

        List<Conge> employeeConge = congeService.getAllCongesByEmployee(employeeId);
        request.setAttribute("employeeConges", employeeConge);
        request.getRequestDispatcher("EmployeeDashboard/employeeConges.jsp").forward(request, response);
    }


    private void handleListByAdminAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Conge> employeeConges = congeService.getAllConges();
        request.setAttribute("employeeConges", employeeConges);
        NotificationService notificationService = new NotificationService();
        List<Notification> notifications = notificationService.findAllNotifications();


        request.setAttribute("notifications", notifications);
        request.getRequestDispatcher("AdminDashboard/adminConges.jsp").forward(request, response);
    }

    private void handleAcceptCongeAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long congeId = Long.parseLong(request.getParameter("congeId"));
        String employeeEmail = request.getParameter("employeeEmail");

        // Retrieve and update the leave request
        Conge conge = congeService.getCongeById(congeId);
        if (conge != null) {
            conge.setStatut(StatutConge.APPROUVE);

            long diffInMillies = Math.abs(conge.getDateFin().getTime() - conge.getDateDebut().getTime());
            System.out.println("diffInMillies" + diffInMillies);
            long numberOfDaysRequested = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS) ;
            long  soldeConges = conge.getEmploye().getSoldeConges() ;
            Employee employee = conge.getEmploye();
            System.out.println("numberOfDaysRequested" + numberOfDaysRequested);
            soldeConges -= numberOfDaysRequested;
            employee.setSoldeConges(soldeConges);
            System.out.println("soldeConges" + soldeConges);
            EmployeeService EmployeeService = new EmployeeService();
            EmployeeService.updateEmployee(conge.getEmploye().getId() , employee);

            congeService.updateConge(conge);
            String subject = "Votre demande de congé a été approuvée";
            String body = "Félicitations, votre demande de congé a été approuvée.";
            sendEmailNotification(employeeEmail, subject, body);

            response.sendRedirect("conges?action=ListParAdmin");
        } else {
            handleError(request, response, "Demande de congé introuvable.");
        }
    }

    private void handleRejectCongeAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long congeId = Long.parseLong(request.getParameter("congeId")); // Use long instead of int for congeId
        String employeeEmail = request.getParameter("employeeEmail"); // Get the employee's email

        // Retrieve and update the leave request
        Conge conge = congeService.getCongeById(congeId);
        if (conge != null) {
            conge.setStatut(StatutConge.REJETE);
            congeService.updateConge(conge);

            // Notify the employee
            String subject = "Votre demande de congé a été REJETE";
            String body = "Votre demande de congé a été REJETE.";
            sendEmailNotification(employeeEmail, subject, body);

            response.sendRedirect("conges?action=ListParAdmin");
        } else {
            handleError(request, response, "Demande de congé introuvable.");
        }
    }

    private void sendEmailNotification(String employeeEmail, String subject, String body) {
        try {
            Gmail.sendEmail(employeeEmail, subject, body);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleError(HttpServletRequest request, HttpServletResponse response, String errorMessage) throws ServletException, IOException {
        request.setAttribute("errorMessage", errorMessage);
        request.getRequestDispatcher("AdminDashboard/adminConges.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Get form parameters
        String dateDebutParam = request.getParameter("dateDebut");
        String dateFinParam = request.getParameter("dateFin");
        String motif = request.getParameter("motif");

        // Get the authenticated user from the session
        Object authenticatedUser = session.getAttribute("Auth");
        if (!(authenticatedUser instanceof Employee)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not authenticated.");
            return;
        }
        long employeeId = ((Employee) authenticatedUser).getId();

        // Validate inputs
        if (dateDebutParam == null || dateFinParam == null || motif == null) {
            request.setAttribute("errorMessage", "All fields are required..");
            request.getRequestDispatcher("EmployeeDashboard/AddConges.jsp").forward(request, response);
            return;
        }

        // Validate and convert date strings to Date objects
        Date dateDebut = parseDate(dateDebutParam, response);
        Date dateFin = parseDate(dateFinParam, response);
        if (dateDebut == null || dateFin == null) return;

        // Check if dateDebut is before dateFin
        if (dateDebut.after(dateFin)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Date Debut must be before Date Fin.");
            return;
        }

        List<Conge> approvedConges = congeService.getApprovedConges();

        congeService.DejaConge(request, response, dateDebut, dateFin , approvedConges) ;



        // Calculate the number of requested leave days
        long diffInMillies = Math.abs(dateFin.getTime() - dateDebut.getTime());
        long numberOfDaysRequested = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS) + 1;

        // Retrieve the employee and check leave balance
        Employee employee = new EmployeeService().getEmployeeById(employeeId);
        long soldeConges = employee.getSoldeConges();

        // Check if requested days exceed available leave balance
        congeService.ChackSoldeConges(request, response, numberOfDaysRequested , soldeConges ) ;

        // Create a new Conge object
        Conge conge = new Conge();
        conge.setDateDebut(dateDebut);
        conge.setDateFin(dateFin);
        conge.setMotif(motif);
        conge.setEmploye(employee);
        conge.setStatut(StatutConge.EN_ATTENTE);

        // Handle the PDF file upload
        Part pdfFile = request.getPart("pdfFile");
        if (pdfFile != null && pdfFile.getSize() > 0) {
            conge.setPdf(pdfFile.getInputStream().readAllBytes());
        }

        try {
            // Save the leave request and handle the PDF if present
            congeService.createConge(conge, pdfFile != null ? pdfFile.getInputStream().readAllBytes() : null);

            // Send notification
            String sujet = "Nouvelle demande de congé soumise";
            String message = "Employee " + employee.getName() + " a soumis une nouvelle demande de congé.";
            Notification notification = new Notification(sujet, message, new Date());
            NotificationService notificationService = new NotificationService();
            notificationService.createNotification(notification);

            // Redirect to the list of leaves after successful request
            response.sendRedirect("conges?action=List");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error while saving the Conge.");
        }
    }

    private Date parseDate(String dateString, HttpServletResponse response) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            return sdf.parse(dateString);
        } catch (ParseException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid date format. Please use yyyy-MM-dd.");
            return null;
        }
    }



}
