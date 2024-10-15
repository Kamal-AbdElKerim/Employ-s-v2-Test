package model.Auth;


import model.OffreEmploi;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "RH")
public class RH extends User {

    @OneToMany(mappedBy = "rh", cascade = CascadeType.ALL)
    private List<OffreEmploi> offresEmploi;

    public RH() {
        super();
    }

    public RH(String name, String email, String password) {
        super(name, email, password, Role.RH);
    }

    public List<OffreEmploi> getOffresEmploi() {
        return offresEmploi;
    }

    public void setOffresEmploi(List<OffreEmploi> offresEmploi) {
        this.offresEmploi = offresEmploi;
    }
}


