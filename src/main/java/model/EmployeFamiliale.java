package model;

import model.Auth.Admin;
import model.Auth.Employee;

import javax.persistence.*;

@Entity
@Table(name = "Employe_familiale")
public class EmployeFamiliale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long allocationId;



    private int nombreEnfants;
    private double salaire;
    private double montantAllocation;

    @OneToOne
    @JoinColumn(name = "employee_id", nullable = false) // Foreign key to Employee
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    public EmployeFamiliale() {
        // Optionally initialize default values
    }

    public EmployeFamiliale(Long allocationId, int nombreEnfants, double salaire, double montantAllocation, Employee employee) {
        this.allocationId = allocationId;
        this.nombreEnfants = nombreEnfants;
        this.salaire = salaire;
        this.montantAllocation = montantAllocation;
        this.employee = employee;
    }
    public EmployeFamiliale( int nombreEnfants, double salaire, double montantAllocation, Employee employee) {
        this.nombreEnfants = nombreEnfants;
        this.salaire = salaire;
        this.montantAllocation = montantAllocation;
        this.employee = employee;
    }



    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Long getAllocationId() {
        return allocationId;
    }

    public void setAllocationId(Long allocationId) {
        this.allocationId = allocationId;
    }

    public int getNombreEnfants() {
        return nombreEnfants;
    }

    public void setNombreEnfants(int nombreEnfants) {
        this.nombreEnfants = nombreEnfants;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

    public double getMontantAllocation() {
        return montantAllocation;
    }

    public void setMontantAllocation(double montantAllocation) {
        this.montantAllocation = montantAllocation;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "EmployeFamiliale{" +
                "allocationId=" + allocationId +
                ", nombreEnfants=" + nombreEnfants +
                ", salaire=" + salaire +
                ", montantAllocation=" + montantAllocation +
                ", employeEmail=" + (employee != null ? employee.getEmail() : "null") +  // Display employee ID or a suitable property
                ", admin=" + admin +
                '}';
    }
}

