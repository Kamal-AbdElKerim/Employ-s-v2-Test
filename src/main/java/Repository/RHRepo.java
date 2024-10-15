package Repository;

import model.Auth.RH;
import utility.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

public class RHRepo {

    public RH findRHByEmail(String email) {
        EntityManager em = null; // Initialize EntityManager to null
        EntityTransaction transaction = null; // Initialize EntityTransaction to null
        RH rh = null;

        try {
            em = JPAUtil.getEntityManager();  // Get a new EntityManager instance
            transaction = em.getTransaction();
            transaction.begin();  // Start the transaction

            rh = em.createQuery("SELECT r FROM RH r WHERE r.email = :email", RH.class)
                    .setParameter("email", email)
                    .getSingleResult();

            transaction.commit();  // Commit the transaction
        } catch (NoResultException e) {
            // Handle case where no result is found
            System.out.println("No RH found with email: " + email);
            if (transaction != null && transaction.isActive()) {
                transaction.rollback(); // Rollback if active
            }
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();  // Rollback in case of error
            }
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();  // Ensure to close the EntityManager
            }
        }
        return rh;  // Return the found RH object or null
    }
}
