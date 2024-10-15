package model;

import model.Auth.StatusCandidature;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity()
@Table(name = "candidature")
public class Candidature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name ;
    private String email ;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> skills;

    private Date dateCandidature;

    @Enumerated(EnumType.STRING)
    private StatusCandidature statusCandidature;

    @ManyToMany
    @JoinTable(
            name = "candidature_offre",
            joinColumns = @JoinColumn(name = "candidature_id"),
            inverseJoinColumns = @JoinColumn(name = "offre_id")
    )
    private List<OffreEmploi> offres;

    public Candidature() {
        super();
    }

    public Candidature(String name, String email,   List<String> skills, Date dateCandidature, List<OffreEmploi> offres) {
        this.skills = skills;
        this.dateCandidature = dateCandidature;
        this.offres = offres;
        this.name = name;
        this.email = email;
        this.statusCandidature = StatusCandidature.ATTENDRE;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }



    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public StatusCandidature getStatusCandidature() {
        return statusCandidature;
    }

    public void setStatusCandidature(StatusCandidature statusCandidature) {
        this.statusCandidature = statusCandidature;
    }



    public Date getDateCandidature() {
        return dateCandidature;
    }

    public void setDateCandidature(Date dateCandidature) {
        this.dateCandidature = dateCandidature;
    }

    public List<OffreEmploi> getOffres() {
        return offres;
    }

    public void setOffres(List<OffreEmploi> offres) {
        this.offres = offres;
    }

    @Override
    public String toString() {
        return "Candidature{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", skills=" + skills +
                ", dateCandidature=" + dateCandidature +
                ", statusCandidature=" + statusCandidature +
                ", offres=" + offres +
                '}';
    }
}
