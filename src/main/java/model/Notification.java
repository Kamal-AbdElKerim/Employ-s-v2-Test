package model;

import model.Auth.Admin;
import model.Auth.Employee;

import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name="Notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;



    private String sujet;
    private String message;
    private Date dateNotification;


    public Notification() {
    }
    public Notification( String sujet,String message, Date dateNotification) {
        this.sujet = sujet;
        this.dateNotification = dateNotification;
        this.message = message ;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public String getsujet() {
        return sujet;
    }

    public void setsujet(String sujet) {
        this.sujet = sujet;
    }

    public Date getDateNotification() {
        return dateNotification;
    }

    public void setDateNotification(Date dateNotification) {
        this.dateNotification = dateNotification;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "notificationId=" + notificationId +
                ", sujet='" + sujet + '\'' +
                ", message='" + message + '\'' +
                ", dateNotification=" + dateNotification +
                '}';
    }
}

