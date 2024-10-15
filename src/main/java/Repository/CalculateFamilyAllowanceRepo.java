package Repository;

import model.EmployeFamiliale;
import utility.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import java.util.List;

public class CalculateFamilyAllowanceRepo {

    // Method to calculate family allowance for a given employee
    public double calculateFamilyAllowance(EmployeFamiliale employeFamiliale) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        double familyAllowance = 0.0;

        try {
            transaction.begin();

            if (employeFamiliale != null) {
                if (employeFamiliale.getEmployee() == null) {
                    System.out.println("Employee is null.");
                    return familyAllowance; // Handle as needed
                }

                // Validate Employee fields
                if (employeFamiliale.getEmployee().getNumeroSecuriteSociale() == null) {
                    System.out.println("Employee's social security number is null.");
                    return familyAllowance; // Handle as needed
                }

                // Any other necessary checks
                entityManager.persist(employeFamiliale);
            } else {
                System.out.println("EmployeFamiliale is null.");
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

        return familyAllowance;
    }

    // Method to retrieve family allowances for all employees
    public List<EmployeFamiliale> getAllFamilyAllowances() {
        EntityManager entityManager = JPAUtil.getEntityManager();
        List<EmployeFamiliale> allowances = null;

        try {
            allowances = entityManager.createQuery("FROM EmployeFamiliale", EmployeFamiliale.class).getResultList();
        } finally {
            entityManager.close(); // Ensure to close the EntityManager
        }

        return allowances;
    }

    // Method to get family allowance for a specific employee
    public EmployeFamiliale getFamilyAllowanceByEmployeeId(Long employeeId) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EmployeFamiliale allowance = null;

        try {
            // Start a transaction
            entityManager.getTransaction().begin();

            // Use 'employee' field in the query, as it references the Employee entity
            allowance = entityManager.createQuery("SELECT fa FROM EmployeFamiliale fa WHERE fa.employee.id = :employeeId", EmployeFamiliale.class)
                    .setParameter("employeeId", employeeId)
                    .getSingleResult();

            // Commit the transaction
            entityManager.getTransaction().commit();

        } catch (NoResultException e) {
            System.out.println("No family allowance found for employee ID: " + employeeId);
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close(); // Ensure to close the EntityManager
        }

        return allowance;
    }


    public boolean updateFamilyAllowance(Long employeFamilialeId, EmployeFamiliale updatedEmployeFamiliale) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        boolean isUpdated = false;

        try {
            transaction.begin();

            // Find the existing EmployeFamiliale entity
            EmployeFamiliale existingAllowance = entityManager.find(EmployeFamiliale.class, employeFamilialeId);

            if (existingAllowance != null) {

                existingAllowance.setNombreEnfants(updatedEmployeFamiliale.getNombreEnfants()); // Example field
                existingAllowance.setMontantAllocation(updatedEmployeFamiliale.getMontantAllocation());


                entityManager.merge(existingAllowance);
                isUpdated = true;
            } else {
                System.out.println("No family allowance found for ID: " + employeFamilialeId);
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

        return isUpdated;
    }


    public EmployeFamiliale getFamilyAllowancesById(Long employeFamilialeId) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EmployeFamiliale allowance = null;

        try {
            // Start a transaction
            entityManager.getTransaction().begin();

            // Use 'employeFamilialeId' to find the specific family allowance
            allowance = entityManager.find(EmployeFamiliale.class, employeFamilialeId);

            // Commit the transaction
            entityManager.getTransaction().commit();

            // Check if the allowance was found
            if (allowance == null) {
                System.out.println("No family allowance found for ID: " + employeFamilialeId);
            }

        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close(); // Ensure to close the EntityManager
        }

        return allowance;
    }


}
