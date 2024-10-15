package Repository;


import model.Notification;
import utility.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class NotificationRepo {

    public Notification createNotification(Notification notification) {
        EntityManager em = null;
        EntityTransaction transaction = null;

        try {
            em = JPAUtil.getEntityManager();
            transaction = em.getTransaction();
            transaction.begin();

            em.persist(notification);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback(); // Rollback in case of error
            }
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close(); // Close EntityManager
            }
        }

        return notification; // Return the created notification
    }


    public List<Notification> findAllNotifications() {
        EntityManager em = null;
        List<Notification> notifications = null;

        try {
            em = JPAUtil.getEntityManager();
            notifications = em.createQuery("SELECT n FROM Notification n ORDER BY n.dateNotification DESC", Notification.class)
                    .getResultList();
            System.out.println("Fetched Notifications: " + notifications); // Add this for debugging

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }

        return notifications; // Return the list of notifications
    }

}

