package Repository;

import model.OffreEmploi;
import model.StatutOffre;
import utility.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import java.util.List;

public class OffreRepo {

    // Create a new job offer
    public boolean createOffre(OffreEmploi offre) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        boolean isCreated = false;

        try {
            transaction.begin();



            // Set the default status to active or open
            offre.setStatut(StatutOffre.ACTIVE);

            entityManager.persist(offre);
            transaction.commit();
            isCreated = true;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

        return isCreated;
    }

    // Read/Get a single job offer by ID
    public OffreEmploi findOffreById(Long id) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        OffreEmploi offre = null;

        try {
            offre = entityManager.find(OffreEmploi.class, id);
        } catch (NoResultException e) {
            System.out.println("No offer found with ID: " + id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

        return offre;
    }

    // Read/Get all job offers
    public List<OffreEmploi> getAllOffres() {
        EntityManager entityManager = JPAUtil.getEntityManager();
        List<OffreEmploi> offres = null;

        try {
            offres = entityManager.createQuery("SELECT o FROM OffreEmploi o order by o.datePublication desc ", OffreEmploi.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

        return offres;
    }


    // Delete a job offer by ID
    public boolean deleteOffre(Long id) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        boolean isDeleted = false;

        try {
            transaction.begin();

            OffreEmploi offre = entityManager.find(OffreEmploi.class, id);
            if (offre != null) {
                entityManager.remove(offre);
                transaction.commit();
                isDeleted = true;
            } else {
                System.out.println("Offer not found with ID: " + id);
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

        return isDeleted;
    }
}
