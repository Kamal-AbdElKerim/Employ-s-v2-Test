package model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "rapport")
public class Rapport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rapportId;

    private String typeRapport;
    private Date periode;
    private String donnees;

    public Rapport(Long rapportId, String typeRapport, Date periode, String donnees) {
        this.rapportId = rapportId;
        this.typeRapport = typeRapport;
        this.periode = periode;
        this.donnees = donnees;
    }

    public Long getRapportId() {
        return rapportId;
    }

    public void setRapportId(Long rapportId) {
        this.rapportId = rapportId;
    }

    public String getTypeRapport() {
        return typeRapport;
    }

    public void setTypeRapport(String typeRapport) {
        this.typeRapport = typeRapport;
    }

    public Date getPeriode() {
        return periode;
    }

    public void setPeriode(Date periode) {
        this.periode = periode;
    }

    public String getDonnees() {
        return donnees;
    }

    public void setDonnees(String donnees) {
        this.donnees = donnees;
    }
}

