package model.Auth;


import model.Conge;
import model.EmployeFamiliale;
import model.Notification;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="employees")
@PrimaryKeyJoinColumn(name = "id")
public class Employee extends User {



    @Temporal(TemporalType.DATE)
    private Date dateNaissance;

    private String numeroSecuriteSociale;

    @Temporal(TemporalType.DATE)
    private Date dateEmbauche;

    private Long soldeConges;

    private String adresse;
    private String telephone;

    @Column(name = "department")
    private String department;

    @Column(name = "position")
    private String position;

    @Column(name = "password")
    private String password;

    @Column(name = "created_at")
    private LocalDateTime createdAt;





    @OneToMany(mappedBy = "employe", cascade = CascadeType.ALL)
    private List<Conge> conges;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    private EmployeFamiliale employeFamiliale;


    public Employee() {
        super();
    }

    public Employee(String name, String email, String password, Date dateNaissance, String numeroSecuriteSociale,
                   Date dateEmbauche, String adresse, String telephone, String department, String position,
                   LocalDateTime createdAt ) {
        super(name, email, password, Role.EMPLOYE);  // Role is fixed for Employee
        this.dateNaissance = dateNaissance;
        this.numeroSecuriteSociale = numeroSecuriteSociale;
        this.dateEmbauche = dateEmbauche;
        this.adresse = adresse;
        this.telephone = telephone;
        this.department = department;
        this.position = position;
        this.createdAt = LocalDateTime.now();
        this.password = password ;
        this.soldeConges = 30L;
    }



    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }




    public List<Conge> getConges() {
        return conges;
    }

    public void setConges(List<Conge> conges) {
        this.conges = conges;
    }

    public EmployeFamiliale getEmployeFamiliale() {
        return employeFamiliale;
    }

    public void setEmployeFamiliale(EmployeFamiliale employeFamiliale) {
        this.employeFamiliale = employeFamiliale;
    }

    // Getters and Setters
    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getNumeroSecuriteSociale() {
        return numeroSecuriteSociale;
    }

    public void setNumeroSecuriteSociale(String numeroSecuriteSociale) {
        this.numeroSecuriteSociale = numeroSecuriteSociale;
    }

    public Date getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(Date dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getSoldeConges() {
        return soldeConges;
    }

    public void setSoldeConges(Long soldeConges) {
        this.soldeConges = soldeConges;
    }


}
