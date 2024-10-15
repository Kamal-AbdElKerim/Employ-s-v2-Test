package Service;

import Repository.CongeRepo;
import model.Auth.Employee;
import model.Conge;
import model.EmployeFamiliale;
import model.StatutConge;
import utility.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

public class CongeService {
    private EmployeeService employeeService =  new EmployeeService();
    private CongeRepo congeRepo = new CongeRepo();


    public void createConge(Conge conge, byte[] pdfData) {
        congeRepo.createConge(conge , pdfData);
    }


    // Method to get all Conges
    public List<Conge> getAllCongesByEmployee(long employeeId) {
       return congeRepo.getAllCongesByEmployee(employeeId);
    }


    public List<Conge> getApprovedCongesByEmployeeId(long employeeId) {
        return congeRepo.getApprovedCongesByEmployeeId(employeeId);
    }

    public List<Conge> getApprovedConges() {
       return congeRepo.getApprovedConges();
    }


    public List<Conge> getAllConges() {
       return congeRepo.getAllConges();
    }


    // Method to get a Conge by ID
    public Conge getCongeById(Long congeId) {
      return congeRepo.getCongeById(congeId);
    }

    // Method to update an existing Conge
    public void updateConge(Conge conge) {
        congeRepo.updateConge(conge);
    }

    // Method to delete a Conge
    public void deleteConge(Long congeId) {
        congeRepo.deleteConge(congeId);
    }

    public void DejaConge(HttpServletRequest request, HttpServletResponse response, Date dateDebut, Date dateFin , List<Conge> approvedConges) throws ServletException, IOException {


        for (Conge conge : approvedConges) {
            Date existingDebut = conge.getDateDebut();
            Date existingFin = conge.getDateFin();

            // Check for overlapping dates
            if ((dateDebut.before(existingFin) && dateFin.after(existingDebut)) ||
                    (dateDebut.equals(existingDebut) || dateFin.equals(existingFin))) {

                request.setAttribute("errorMessage", "Les dates demandées chevauchent des congés déjà approuvés.");
                request.getRequestDispatcher("EmployeeDashboard/AddConges.jsp").forward(request, response);
                return;
            }
        }
    }

    public void  ChackSoldeConges(HttpServletRequest request, HttpServletResponse response,long numberOfDaysRequested , long soldeConges ) throws ServletException, IOException {
        if (numberOfDaysRequested > soldeConges) {
            request.setAttribute("errorMessage", "Le nombre de jours demandés dépasse le solde de congés ("+soldeConges+" jour)  disponible.");
            request.getRequestDispatcher("EmployeeDashboard/AddConges.jsp").forward(request, response);

            return;
        }
    }
}
