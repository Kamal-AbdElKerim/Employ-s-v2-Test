package Repository;
import Service.CalculateFamilyAllowanceService;
import model.Auth.Employee;
import model.Auth.EmployeeChange;
import model.EmployeFamiliale;
import utility.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import java.time.LocalDateTime;
import java.util.List;

public class EmployeeRepo {
    private CalculateFamilyAllowanceService calculateFamilyAllowanceService = new CalculateFamilyAllowanceService();

    // Method to add a new employee
    public void addEmployee(Employee employee) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close(); // Ensure to close the EntityManager
        }
    }

    // Method to update an existing employee
    public void updateEmployee(Long employeeId, Employee updatedEmployee) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            // Find the existing employee
            Employee employee = entityManager.find(Employee.class, employeeId);

            if (employee == null) {
                throw new IllegalArgumentException("Employee not found");
            }

            if (!employee.getName().equals(updatedEmployee.getName())) {
                logChange(employeeId, "name", employee.getName(), updatedEmployee.getName(), entityManager);
                employee.setName(updatedEmployee.getName());
            }
            if (!employee.getEmail().equals(updatedEmployee.getEmail())) {
                logChange(employeeId, "email", employee.getEmail(), updatedEmployee.getEmail(), entityManager);
                employee.setEmail(updatedEmployee.getEmail());
            }
            if (!employee.getPosition().equals(updatedEmployee.getPosition())) {
                logChange(employeeId, "position", employee.getPosition(), updatedEmployee.getPosition(), entityManager);
                employee.setPosition(updatedEmployee.getPosition());
            }

            if (!employee.getAdresse().equals(updatedEmployee.getAdresse())) {
                logChange(employeeId, "adresse", employee.getAdresse(), updatedEmployee.getAdresse(), entityManager);
                employee.setAdresse(updatedEmployee.getAdresse());
            }

            if (!employee.getDateNaissance().equals(updatedEmployee.getDateNaissance())) {
                logChange(employeeId, "dateNaissance", employee.getDateNaissance().toString(), updatedEmployee.getDateNaissance().toString(), entityManager);
                employee.setDateNaissance(updatedEmployee.getDateNaissance());
            }

            if (!employee.getNumeroSecuriteSociale().equals(updatedEmployee.getNumeroSecuriteSociale())) {
                logChange(employeeId, "numeroSecuriteSociale", employee.getNumeroSecuriteSociale(), updatedEmployee.getNumeroSecuriteSociale(), entityManager);
                employee.setNumeroSecuriteSociale(updatedEmployee.getNumeroSecuriteSociale());
            }

            if (!employee.getDateEmbauche().equals(updatedEmployee.getDateEmbauche())) {
                logChange(employeeId, "dateEmbauche", employee.getDateEmbauche().toString(), updatedEmployee.getDateEmbauche().toString(), entityManager);
                employee.setDateEmbauche(updatedEmployee.getDateEmbauche());
            }

            if (!employee.getTelephone().equals(updatedEmployee.getTelephone())) {
                logChange(employeeId, "telephone", employee.getTelephone(), updatedEmployee.getTelephone(), entityManager);
                employee.setTelephone(updatedEmployee.getTelephone());
            }

            if (!employee.getDepartment().equals(updatedEmployee.getDepartment())) {
                logChange(employeeId, "department", employee.getDepartment(), updatedEmployee.getDepartment(), entityManager);
                employee.setDepartment(updatedEmployee.getDepartment());
            }
            if (!employee.getSoldeConges().equals(updatedEmployee.getSoldeConges())) {
                employee.setSoldeConges(updatedEmployee.getSoldeConges());
            }

            entityManager.merge(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close(); // Ensure to close the EntityManager
        }
    }

    // Method to retrieve employee change history
    public List<EmployeeChange> getEmployeeChangeHistory(Long employeeId) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        List<EmployeeChange> changes = null;

        try {
            changes = entityManager.createQuery("SELECT ec FROM EmployeeChange ec WHERE ec.employeeId = :employeeId", EmployeeChange.class)
                    .setParameter("employeeId", employeeId)
                    .getResultList();
        } finally {
            entityManager.close(); // Ensure to close the EntityManager
        }

        return changes;
    }

    // Method to log changes in employee details
    private void logChange(Long employeeId, String fieldChanged, String oldValue, String newValue, EntityManager entityManager) {
        EmployeeChange change = new EmployeeChange(employeeId, LocalDateTime.now(), fieldChanged, oldValue, newValue);
        entityManager.persist(change);
    }

    // Method to get all employees
    public List<Employee> getAllEmployees() {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        List<Employee> employees = null;

        try {
            transaction.begin();
            employees = entityManager.createQuery("FROM Employee", Employee.class).getResultList();

            for (Employee employee : employees) {


                EmployeFamiliale employeFamiliale = calculateFamilyAllowanceService.getFamilyAllowanceByEmployeeId(employee.getId());
                System.out.println("employeFamiliale islam" + employeFamiliale);

                if (employeFamiliale != null) {
                    employee.setEmployeFamiliale(employeFamiliale);
                    employeFamiliale.setEmployee(employee);
                } else {
                    employee.setEmployeFamiliale(null);
                }
            }

            transaction.commit();
        } catch (Exception e) {
            // Logging the exception for better debugging
            e.printStackTrace();
            if (transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            entityManager.close();
        }

        return employees;
    }


    // Method to retrieve an employee by ID
    public Employee getEmployeeById(long id) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        Employee employee = null;

        try {
            employee = entityManager.find(Employee.class, id);
            System.out.println(employee);


        } finally {
            entityManager.close(); // Ensure to close the EntityManager
        }

        return employee;
    }

    // Method to delete an employee by ID
    public void deleteEmployee(Long employeeId) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Employee employee = entityManager.find(Employee.class, employeeId);
            if (employee != null) {
                entityManager.remove(employee);
            } else {
                System.out.println("Employee with ID " + employeeId + " not found.");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close(); // Ensure to close the EntityManager
        }
    }

    public Employee findEmployeByEmail(String email) {
        EntityManager em = JPAUtil.getEntityManager();
        Employee employee = null;

        try {
            // Create the query to find the employee by email
            employee = em.createQuery("SELECT e FROM Employee e WHERE e.email = :email", Employee.class)
                    .setParameter("email", email)
                    .getSingleResult(); // Fetch a single result

        } catch (NoResultException e) {
            System.out.println("No employee found with email: " + email); // Handle case where no employee is found
        } catch (Exception e) {
            e.printStackTrace(); // Log other exceptions
        } finally {
            em.close(); // Close the EntityManager
        }

        return employee; // Return the found employee or null
    }
}
