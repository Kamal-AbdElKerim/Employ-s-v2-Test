package Service ;
import Repository.EmployeeRepo;
import model.Auth.Employee;
import model.Auth.EmployeeChange;
import model.EmployeFamiliale;
import org.hibernate.Hibernate;
import utility.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import java.time.LocalDateTime;
import java.util.List;

public class EmployeeService {
    private CalculateFamilyAllowanceService calculateFamilyAllowanceService = new CalculateFamilyAllowanceService();
    private EmployeeRepo employeeRepo =new EmployeeRepo();

    // Method to add a new employee
    public void addEmployee(Employee employee) {
        employeeRepo.addEmployee(employee);
    }

    // Method to update an existing employee
    public void updateEmployee(Long employeeId, Employee updatedEmployee) {
        employeeRepo.updateEmployee(employeeId, updatedEmployee);
    }

    // Method to retrieve employee change history
    public List<EmployeeChange> getEmployeeChangeHistory(Long employeeId) {
       return employeeRepo.getEmployeeChangeHistory(employeeId);
    }



    // Method to get all employees
    public List<Employee> getAllEmployees() {
        return employeeRepo.getAllEmployees();
    }


    // Method to retrieve an employee by ID
    public Employee getEmployeeById(long id) {
       return employeeRepo.getEmployeeById(id);
    }

    // Method to delete an employee by ID
    public void deleteEmployee(Long employeeId) {
        employeeRepo.deleteEmployee(employeeId);
    }

    public Employee findEmployeByEmail(String email) {
       return employeeRepo.findEmployeByEmail(email);
    }
}
