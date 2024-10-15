package utility;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
    private static EntityManagerFactory entityManagerFactory;

    static {
        try {
            // Create the EntityManagerFactory using the persistence unit defined in persistence.xml
            entityManagerFactory = Persistence.createEntityManagerFactory("MaPersistenceUnit");
        } catch (Throwable ex) {
            System.err.println("Initial EntityManagerFactory creation failed: " + ex);
            throw new ExceptionInInitializerError(ex); // Ensure the application fails fast
        }
    }

    // Method to get an EntityManager
    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    // Method to close the EntityManagerFactory
    public static void closeEntityManagerFactory() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }

    public static void setEntityManagerFactory(EntityManagerFactory emf) {
        entityManagerFactory = emf;
    }
}
