package Service;


import Repository.NotificationRepo;
import model.Notification;
import model.Auth.Admin;
import utility.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class NotificationService {
    private NotificationRepo notificationRepo = new NotificationRepo();

    public Notification createNotification(Notification notification) {
       return notificationRepo.createNotification(notification);
    }


    public List<Notification> findAllNotifications() {
        return notificationRepo.findAllNotifications();
    }

}

