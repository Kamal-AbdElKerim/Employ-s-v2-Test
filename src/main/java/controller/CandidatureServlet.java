package controller;





import Service.OffreService;
import email.Gmail;
import model.Auth.StatusCandidature;
import model.Candidature;
import Service.CandidatureService;
import model.OffreEmploi;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/candidature")
public class CandidatureServlet extends HttpServlet {

    private CandidatureService candidatureService;
    private OffreService offreService = new OffreService();

    @Override
    public void init() throws ServletException {
        // Initialize the CandidatureService manually
        candidatureService = new CandidatureService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String id = req.getParameter("id");
        Long candidatureId = null;
        if (id != null) {
             candidatureId = Long.parseLong(id);

        }


        if (action != null) {
            switch (action) {

                case "list":
                    listCandidatures(req, resp);
                    break;

                case "accept":
                    AcceptCandidature(req, resp , candidatureId) ;



                    break;
                case "reject":
                    RejectCandidature(req, resp , candidatureId) ;



                    break;
                default:
                    resp.getWriter().write("Action not recognized");
                    break;
            }
        } else {
            req.getRequestDispatcher("Candidature/JoinOffre.jsp").forward(req, resp);

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action != null) {
            switch (action) {
                case "add":
                    addCandidature(req, resp);
                    break;
                case "list":
                    listCandidatures(req, resp);
                    break;

                default:
                    resp.getWriter().write("Action not recognized");
                    break;
            }
        } else {
            resp.getWriter().write("No action provided");
        }
    }

    // Handle listing all candidatures
    private void listCandidatures(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        List<Candidature> candidatures = candidatureService.getAllCandidatures();

        // Get the desired status from request parameters
        String statusParam = request.getParameter("status");
        StatusCandidature status = null;

        // Convert the string status to the StatusCandidature enum
        try {
            if (statusParam != null) {
                status = StatusCandidature.valueOf(statusParam.toUpperCase());
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid status value: " + statusParam);
            status = null; // Set status to null to indicate no filtering
        }

        String skillsParam = request.getParameter("skills");
        String selectedSkill = (skillsParam != null && !skillsParam.trim().isEmpty()) ? skillsParam.trim() : null;


        // Filter by status
        if (status != null) {
            StatusCandidature finalStatus = status;
            candidatures = candidatures.stream()
                    .filter(candidature -> candidature.getStatusCandidature() == finalStatus)
                    .collect(Collectors.toList());
        }

        if (selectedSkill != null) {
            System.out.println("Selected Skill: " + selectedSkill);

            candidatures = candidatures.stream()
                    .filter(candidature -> {
                        // Normalize selected skill to lowercase
                        String normalizedSelectedSkill = selectedSkill.toLowerCase();

                        // Normalize candidature's skills to lowercase
                        List<String> candidatureSkills = candidature.getSkills().stream()
                                .map(String::toLowerCase)
                                .collect(Collectors.toList());

                        // Debugging output
                        System.out.println("Candidature skills: " + candidatureSkills);

                        // Check if the normalized selected skill is in the candidature's skills
                        return candidatureSkills.contains(normalizedSelectedSkill);
                    })
                    .collect(Collectors.toList());
        }



        // Set the filtered candidatures as a request attribute
        request.setAttribute("candidature", candidatures);
        request.setAttribute("selectedStatus", statusParam);


        // Forward to the JSP page
        request.getRequestDispatcher("RhDashboard/ListRegisterOffre.jsp").forward(request, response);
    }


    private void AcceptCandidature(HttpServletRequest request, HttpServletResponse response , long candidatureId) throws IOException, ServletException {
        candidatureService.acceptCandidature(candidatureId);
        Candidature candidature =   candidatureService.getCandidatureById(candidatureId);
        String subject = "Reject Candidature";
        String body = "Hello you Register has Accept";
        sendEmailNotification(candidature.getEmail() ,subject ,body );
        response.sendRedirect("candidature?action=list");
    }

    private void RejectCandidature(HttpServletRequest request, HttpServletResponse response , long candidatureId) throws IOException, ServletException {
        candidatureService.rejectCandidature(candidatureId);
        Candidature candidature =   candidatureService.getCandidatureById(candidatureId);
        String subject = "Reject Candidature";
        String body = "Hello you Register has Reject";
        sendEmailNotification(candidature.getEmail() ,subject ,body );
        response.sendRedirect("candidature?action=list");
    }

    // Handle adding a candidature
    private void addCandidature(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String[] skills = request.getParameterValues("skills");
        String idString = request.getParameter("id");

        long id = 0;
        if (idString != null && !idString.isEmpty()) {
            id = Long.parseLong(idString);
        }

        // Check if the email is unique for this job offer
        if (!candidatureService.isEmailUniqueForOffre(email, id)) {
            // Email already exists for this job offer, set an error message and forward back to the form
            request.setAttribute("errorMessage", "This email has already been used for a candidature for this job offer.");
            request.getRequestDispatcher("Candidature/JoinOffre.jsp").forward(request, response);
            return;
        }

        // Retrieve the job offer using the provided ID
        OffreEmploi offreEmploi = offreService.findOffreById(id);

        // Create a new Candidature object
        Candidature candidature = new Candidature(fullName, email, Arrays.asList(skills), new Date(), List.of(offreEmploi));

        // Add the candidature to the database
        candidatureService.addCandidature(candidature);

        String subject = "Register for a job";
        String body = "Hello you Register for a job";
        sendEmailNotification(email ,subject ,body );

        request.getRequestDispatcher("HomePage").forward(request, response);


    }



    // Handle deleting a candidature



    private void sendEmailNotification(String employeeEmail, String subject, String body) {
        try {
            Gmail.sendEmail(employeeEmail, subject, body);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
