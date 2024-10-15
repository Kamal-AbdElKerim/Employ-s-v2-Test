package Repository;
import model.Auth.Admin;
import utility.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

public class AdminRepo {

    public AdminRepo() {}

    public Admin findAdminByEmail(String email) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        Admin admin = null;

        try {
            transaction.begin();
            admin = entityManager.createQuery("SELECT a FROM Admin a WHERE a.email = :email", Admin.class)
                    .setParameter("email", email)
                    .getSingleResult();
            transaction.commit();
            System.out.println("admin" + admin);
        } catch (NoResultException e) {
            // Handle case where no result is found
            System.out.println("No Admin found with email: " + email);
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();  // Rollback in case of error
            }
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();  // Ensure to close the EntityManager
            }        }

        return admin;
    }





}
