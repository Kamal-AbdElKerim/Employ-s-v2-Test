package model;



import model.Auth.RH;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "offre_emploi")
public class OffreEmploi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long offreId;

    private String description;
    private String criteresRequis;
    private LocalDate datePublication;

    @Enumerated(EnumType.STRING)
    private StatutOffre statut;

    // Many offers can be created by one RH

    @ManyToMany(mappedBy = "offres", cascade = CascadeType.ALL)
    private List<Candidature> candidatures;

    @ManyToOne
    @JoinColumn(name = "rh_id", nullable = false) // Foreign key to link to RH
    private RH rh; // Many offers can be created by one RH

    public OffreEmploi(Long offreId, String description, String criteresRequis, LocalDate datePublication, StatutOffre statut, List<Candidature> candidatures, RH rh) {
        this.offreId = offreId;
        this.description = description;
        this.criteresRequis = criteresRequis;
        this.datePublication = datePublication;
        this.statut = statut;
        this.candidatures = candidatures;
        this.rh = rh;
    }

    public OffreEmploi() {

    }

    public Long getOffreId() {
        return offreId;
    }

    public void setOffreId(Long offreId) {
        this.offreId = offreId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCriteresRequis() {
        return criteresRequis;
    }

    public void setCriteresRequis(String criteresRequis) {
        this.criteresRequis = criteresRequis;
    }

    public LocalDate getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(LocalDate datePublication) {
        this.datePublication = datePublication;
    }

    public StatutOffre getStatut() {
        return statut;
    }

    public void setStatut(StatutOffre statut) {
        this.statut = statut;
    }

    public List<Candidature> getCandidatures() {
        return candidatures;
    }

    public void setCandidatures(List<Candidature> candidatures) {
        this.candidatures = candidatures;
    }

    public RH getRh() {
        return rh;
    }

    public void setRh(RH rh) {
        this.rh = rh;
    }

    public void verifierStatut() {
        if (LocalDate.now().isAfter(datePublication.plusDays(30))) {
            this.statut = StatutOffre.FERME;
        }
    }


}
