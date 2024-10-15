package Service;

import Repository.EmployeeRepo;
import model.Auth.Employee;
import model.Auth.EmployeeChange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepo employeeRepo;

    private Employee employee;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        employee = new Employee();
        employee.setId(1L);
        employee.setEmail("test@example.com");
        // Set other employee properties as needed
    }

    @Test
    void testAddEmployee() {
        // Act
        employeeService.addEmployee(employee);

        // Assert
        verify(employeeRepo, times(1)).addEmployee(employee);
    }

    @Test
    void testUpdateEmployee() {
        // Arrange
        Employee updatedEmployee = new Employee();
        updatedEmployee.setEmail("updated@example.com");

        // Act
        employeeService.updateEmployee(1L, updatedEmployee);

        // Assert
        verify(employeeRepo, times(1)).updateEmployee(1L, updatedEmployee);
    }

    @Test
    void testGetEmployeeById() {
        // Arrange
        when(employeeRepo.getEmployeeById(1L)).thenReturn(employee);

        // Act
        Employee result = employeeService.getEmployeeById(1L);

        // Assert
        assertEquals(employee, result);
    }

    @Test
    void testGetAllEmployees() {
        // Arrange
        when(employeeRepo.getAllEmployees()).thenReturn(Arrays.asList(employee));

        // Act
        List<Employee> employees = employeeService.getAllEmployees();

        // Assert
        assertEquals(1, employees.size());
        assertEquals(employee, employees.get(0));
    }

    @Test
    void testDeleteEmployee() {
        // Act
        employeeService.deleteEmployee(1L);

        // Assert
        verify(employeeRepo, times(1)).deleteEmployee(1L);
    }

    @Test
    void testFindEmployeByEmail() {
        // Arrange
        when(employeeRepo.findEmployeByEmail("test@example.com")).thenReturn(employee);

        // Act
        Employee result = employeeService.findEmployeByEmail("test@example.com");

        // Assert
        assertEquals(employee, result);
    }

    @Test
    void testGetEmployeeChangeHistory() {
        // Arrange
        EmployeeChange change = new EmployeeChange(); // Set up changes if needed
        when(employeeRepo.getEmployeeChangeHistory(1L)).thenReturn(Arrays.asList(change));

        // Act
        List<EmployeeChange> changes = employeeService.getEmployeeChangeHistory(1L);

        // Assert
        assertEquals(1, changes.size());
    }
}
