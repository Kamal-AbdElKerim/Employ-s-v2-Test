package Service;

import Repository.CalculateFamilyAllowanceRepo;
import model.Auth.Employee;
import model.EmployeFamiliale;
import utility.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import java.util.List;

public class CalculateFamilyAllowanceService {
    private CalculateFamilyAllowanceRepo calculateFamilyAllowanceRepo = new CalculateFamilyAllowanceRepo();

    // Method to calculate family allowance for a given employee
    public double calculateFamilyAllowance(EmployeFamiliale employeFamiliale) {
      return   calculateFamilyAllowanceRepo.calculateFamilyAllowance(employeFamiliale);
    }

    // Method to retrieve family allowances for all employees
    public List<EmployeFamiliale> getAllFamilyAllowances() {
        return  calculateFamilyAllowanceRepo.getAllFamilyAllowances();
    }

    // Method to get family allowance for a specific employee
    public EmployeFamiliale getFamilyAllowanceByEmployeeId(Long employeeId) {
       return calculateFamilyAllowanceRepo.getFamilyAllowanceByEmployeeId(employeeId);
    }


    public boolean updateFamilyAllowance(Long employeFamilialeId, EmployeFamiliale updatedEmployeFamiliale) {

       return calculateFamilyAllowanceRepo.updateFamilyAllowance(employeFamilialeId, updatedEmployeFamiliale);
    }


    public EmployeFamiliale getFamilyAllowancesById(Long employeFamilialeId) {
       return calculateFamilyAllowanceRepo.getFamilyAllowancesById(employeFamilialeId);
    }

    public double calculateAllowance(int numberOfChildren, double salary) {
        if (numberOfChildren < 0) {
            return 0.0; // Return 0 if the number of children is negative
        }

        double allowance = 0;

        if (salary < 6000) {
            allowance += Math.min(numberOfChildren, 3) * 300; // First 3 children
            if (numberOfChildren > 3) {
                allowance += (numberOfChildren - 3) * 150; // All additional children
            }
        } else if (salary > 8000) {
            allowance += Math.min(numberOfChildren, 3) * 200; // First 3 children
            if (numberOfChildren > 3) {
                allowance += (numberOfChildren - 3) * 110; // All additional children
            }
        }

        return allowance;
    }


}
