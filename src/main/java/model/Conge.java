package model;

import model.Auth.Employee;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Entity
@Table(name = "conge")
public class Conge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long congeId;

    private Date dateDebut;
    private Date dateFin;
    private String motif;

    @ManyToOne
    @JoinColumn(name = "employe_id")
    private Employee employe;

    @Enumerated(EnumType.STRING)
    private StatutConge statut;

    @Lob
    private byte[] pdf;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Conge() {}
    public Conge(Date dateDebut, Date dateFin) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    // Constructor including the new pdf parameter
    public Conge(Long congeId, Date dateDebut, Date dateFin, String motif, Employee employe, StatutConge statut, byte[] pdf) {
        this.congeId = congeId;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.motif = motif;
        this.employe = employe;
        this.statut = statut;
        this.pdf = pdf; // Set the PDF data
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public Long getCongeId() {
        return congeId;
    }

    public void setCongeId(Long congeId) {
        this.congeId = congeId;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public Employee getEmploye() {
        return employe;
    }

    public void setEmploye(Employee employe) {
        this.employe = employe;
    }

    public StatutConge getStatut() {
        return statut;
    }

    public void setStatut(StatutConge statut) {
        this.statut = statut;
    }

    public byte[] getPdf() {
        return pdf;
    }

    public void setPdf(byte[] pdf) {
        this.pdf = pdf;
    }

    public long getDiffInDays() {
        long diffInMillies = dateFin.getTime() - dateDebut.getTime();
        return TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    @Override
    public String toString() {
        return "Conge{" +
                "congeId=" + congeId +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", motif='" + motif + '\'' +
                ", employeEmail=" + (employe != null ? employe.getEmail() : "null") +  // Display employee ID or a suitable property
                ", statut=" + statut +
                ", pdf=" + Arrays.toString(pdf) +
                '}';
    }


}
