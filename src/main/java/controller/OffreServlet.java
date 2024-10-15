package controller;

import Service.EmployeeService;
import Service.OffreService;
import Service.RHService;
import model.Auth.Employee;
import model.Auth.EmployeeChange;
import model.Auth.RH;
import model.OffreEmploi;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/Offre")
public class OffreServlet extends HttpServlet {
    private OffreService offreService;


    @Override
    public void init() throws ServletException {
        offreService = new OffreService();
    }

    // Handle GET requests for listing all employees
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<OffreEmploi> offres = offreService.getAllOffres();

        for (OffreEmploi offre : offres) {
            offre.verifierStatut(); // This will update the status if the offer is expired
        }

        request.setAttribute("offres", offres);

        request.getRequestDispatcher("RhDashboard/offre.jsp").forward(request, response);
    }


    // Handle POST requests for adding/updating/deleting employees
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String criteresRequis = request.getParameter("criteresRequis");
        String description = request.getParameter("description");
        String datePublicationStr = request.getParameter("datePublication");
        HttpSession session = request.getSession();
        // Validation
        String error = null;
        if (criteresRequis == null || criteresRequis.isEmpty()) {
            error = "Les critères requis sont obligatoires.";
        } else if (description == null || description.isEmpty()) {
            error = "La description est obligatoire.";
        } else if (datePublicationStr == null || datePublicationStr.isEmpty()) {
            error = "La date de publication est obligatoire.";
        }

        LocalDate datePublication = null;
        if (error == null) {
            try {
                datePublication = LocalDate.parse(datePublicationStr);
                // Check if the date is in the future
                if (!datePublication.isAfter(LocalDate.now())) {
                    error = "La date de publication ne peut pas être dans le futur.";
                }
            } catch (Exception e) {
                error = "Date de publication invalide.";
            }
        }

        // If there's an error, return it to the form
        if (error != null) {
            request.setAttribute("error", error);
            request.setAttribute("criteresRequis", criteresRequis);
            request.setAttribute("description", description);
            request.setAttribute("datePublication", datePublicationStr);
doGet(request, response);
return;
        }

        Object authenticatedUser = session.getAttribute("Auth");
        if (!(authenticatedUser instanceof RH)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not authenticated.");
            return;
        }


        OffreEmploi offre = new OffreEmploi();
        offre.setDescription(description);
        offre.setCriteresRequis(criteresRequis);
        offre.setDatePublication(datePublication);
        offre.setRh(((RH) authenticatedUser));


        // Create the offer if validation passed
        boolean isCreated = offreService.createOffre(offre);

        if (isCreated) {
            doGet(request, response);
        } else {
            request.setAttribute("error", "Erreur lors de la création de l'offre.");
            request.getRequestDispatcher("RhDashboard/offre.jsp").forward(request, response);
        }
    }

}
