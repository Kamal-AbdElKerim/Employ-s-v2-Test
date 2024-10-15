package Repository;

import Service.EmployeeService;
import model.Conge;
import model.StatutConge;
import utility.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class CongeRepo {
    private EmployeeService employeeService =  new EmployeeService();


    public void createConge(Conge conge, byte[] pdfData) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            // Set the PDF data before persisting
            if (pdfData != null && pdfData.length > 0) {
                conge.setPdf(pdfData); // Set the PDF data to the Conge object
            }

            em.persist(conge); // Persist the Conge object
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback(); // Rollback if there is an error
            }
            e.printStackTrace(); // Log the exception
        } finally {
            em.close(); // Ensure the EntityManager is closed
        }
    }


    // Method to get all Conges
    public List<Conge> getAllCongesByEmployee(long employeeId) {
        EntityManager em = JPAUtil.getEntityManager();
        List<Conge> conges = null;
        try {
            // Fetch all Conges for the specific employee by employeeId
            conges = em.createQuery("SELECT c FROM Conge c WHERE c.employe.id = :employeeId", Conge.class)
                    .setParameter("employeeId", employeeId)
                    .getResultList();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return conges;
    }


    public List<Conge> getApprovedCongesByEmployeeId(long employeeId) {
        EntityManager em = JPAUtil.getEntityManager();
        List<Conge> conges = null;
        try {
            // Fetch approved Conges for the specific employee by employeeId
            conges = em.createQuery("SELECT c FROM Conge c WHERE c.employe.id = :employeeId AND c.statut = :statut", Conge.class)
                    .setParameter("employeeId", employeeId)
                    .setParameter("statut", StatutConge.APPROUVE) // Only fetch approved leaves
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return conges;
    }

    public List<Conge> getApprovedConges() {
        EntityManager em = JPAUtil.getEntityManager();
        List<Conge> conges = null;
        try {
            // Fetch approved Conges for the specific employee by employeeId
            conges = em.createQuery("SELECT c FROM Conge c WHERE  c.statut = :statut", Conge.class)
                    .setParameter("statut", StatutConge.APPROUVE) // Only fetch approved leaves
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return conges;
    }


    public List<Conge> getAllConges() {
        EntityManager em = JPAUtil.getEntityManager();
        List<Conge> conges = null;
        try {
            // Fetch all Conges for the specific employee by employeeId
            conges = em.createQuery("SELECT c FROM Conge c ", Conge.class)

                    .getResultList();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return conges;
    }


    // Method to get a Conge by ID
    public Conge getCongeById(Long congeId) {
        EntityManager em = JPAUtil.getEntityManager();
        Conge conge = null;
        try {
            conge = em.find(Conge.class, congeId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return conge;
    }

    // Method to update an existing Conge
    public void updateConge(Conge conge) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(conge); // Update the Conge object
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // Method to delete a Conge
    public void deleteConge(Long congeId) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Conge conge = em.find(Conge.class, congeId); // Find Conge by ID
            if (conge != null) {
                em.remove(conge); // Remove the Conge object
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }


}
