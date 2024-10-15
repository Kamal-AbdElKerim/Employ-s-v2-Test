package model.Auth;

import model.EmployeFamiliale;
import model.Notification;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "admins")
public class Admin extends User {

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<EmployeFamiliale> employeFamiliale;



    public Admin() {
        super();
    }

    public Admin(String name, String email, String password) {
        super(name, email, password, Role.ADMIN);  // Role is fixed for Admin
    }

    public List<EmployeFamiliale> getEmployeFamiliale() {
        return employeFamiliale;
    }

    public void setEmployeFamiliale(List<EmployeFamiliale> employeFamiliale) {
        this.employeFamiliale = employeFamiliale;
    }


}

