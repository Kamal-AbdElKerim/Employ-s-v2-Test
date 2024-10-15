package Repository;

import Service.OffreService;
import model.Auth.StatusCandidature;
import model.Candidature;
import model.OffreEmploi;
import utility.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class CandidatureRepo {

    public boolean isEmailUniqueForOffre(String email, long offreId) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Candidature> query = em.createQuery("SELECT c FROM Candidature c JOIN c.offres o WHERE c.email = :email AND o.id = :offreId", Candidature.class);
            query.setParameter("email", email);
            query.setParameter("offreId", offreId);
            return query.getResultList().isEmpty(); // Returns true if no existing candidature with the email is found for the specific job offer
        } catch (Exception ex) {
            ex.printStackTrace(); // Log the exception
            return false;
        } finally {
            em.close(); // Close EntityManager instead of closing the factory
        }
    }

    // Create (Add) a new Candidature
    public void addCandidature(Candidature candidature) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(candidature);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            ex.printStackTrace(); // Log the exception (you can replace with a proper logger)
        } finally {
            em.close(); // Close EntityManager
        }
    }

    // Read (Find by ID) a Candidature
    public Candidature getCandidatureById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        Candidature candidature = null;
        try {
            candidature = em.find(Candidature.class, id);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close(); // Close EntityManager
        }
        return candidature; // Return the found Candidature or null
    }

    // Read (Find all) Candidatures
    public List<Candidature> getAllCandidatures() {
        EntityManager em = JPAUtil.getEntityManager();
        List<Candidature> candidatures = null;
        try {
            TypedQuery<Candidature> query = em.createQuery("SELECT c FROM Candidature c JOIN FETCH c.offres", Candidature.class);
            candidatures = query.getResultList();

            // Manually initialize lazy collections (skills and offres)
            for (Candidature candidature : candidatures) {
                OffreService offreService = new OffreService();
                OffreEmploi offreEmploi = offreService.findOffreById(candidature.getId());
                candidature.setOffres(List.of(offreEmploi));
                candidature.getSkills().size();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close(); // Close EntityManager
        }
        return candidatures;
    }



    // Update an existing Candidature by ID
    public void acceptCandidature(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Candidature candidature = em.find(Candidature.class, id);
            if (candidature != null) {
                candidature.setStatusCandidature(StatusCandidature.ACCEPTE); // Assuming you have a status for accepted
                em.merge(candidature); // Update the candidature
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace(); // Handle exception properly in production code
        } finally {
            em.close(); // Always close the EntityManager
        }
    }

    public void rejectCandidature(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Candidature candidature = em.find(Candidature.class, id);
            if (candidature != null) {
                candidature.setStatusCandidature(StatusCandidature.REJETE); // Assuming you have a status for rejected
                em.merge(candidature); // Update the candidature
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace(); // Handle exception properly in production code
        } finally {
            em.close(); // Always close the EntityManager
        }
    }

    // Delete a Candidature by ID

    // Filter by skills
    public List<Candidature> filterBySkills(List<String> skills) {
        EntityManager em = JPAUtil.getEntityManager();
        List<Candidature> candidatures = null;
        try {
            TypedQuery<Candidature> query = em.createQuery("SELECT c FROM Candidature c WHERE c.skills IN :skills", Candidature.class);
            query.setParameter("skills", skills);
            candidatures = query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close(); // Close EntityManager
        }
        return candidatures; // Return the filtered list of Candidatures
    }
}
