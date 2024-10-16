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
    }

    @Test
    void testAddEmployee() {
        
        employeeService.addEmployee(employee);

        
        verify(employeeRepo, times(1)).addEmployee(employee);
    }

    @Test
    void testUpdateEmployee() {
        
        Employee updatedEmployee = new Employee();
        updatedEmployee.setEmail("updated@example.com");

        
        employeeService.updateEmployee(1L, updatedEmployee);

        
        verify(employeeRepo, times(1)).updateEmployee(1L, updatedEmployee);
    }

    @Test
    void testGetEmployeeById() {
        
        when(employeeRepo.getEmployeeById(1L)).thenReturn(employee);

        
        Employee result = employeeService.getEmployeeById(1L);

        
        assertEquals(employee, result);
    }

    @Test
    void testGetAllEmployees() {
        
        when(employeeRepo.getAllEmployees()).thenReturn(Arrays.asList(employee));

        
        List<Employee> employees = employeeService.getAllEmployees();

        
        assertEquals(1, employees.size());
        
        assertEquals(employee, employees.get(0));
    }

    @Test
    void testDeleteEmployee() {
        
        employeeService.deleteEmployee(1L);

        
        verify(employeeRepo, times(1)).deleteEmployee(1L);
    }

    @Test
    void testFindEmployeByEmail() {
        
        when(employeeRepo.findEmployeByEmail("test@example.com")).thenReturn(employee);

        
        Employee result = employeeService.findEmployeByEmail("test@example.com");

        
        assertEquals(employee, result);
    }

    @Test
    void testGetEmployeeChangeHistory() {
        
        EmployeeChange change = new EmployeeChange(); // Set up changes if needed
        when(employeeRepo.getEmployeeChangeHistory(1L)).thenReturn(Arrays.asList(change));

        
        List<EmployeeChange> changes = employeeService.getEmployeeChangeHistory(1L);

        
        assertEquals(1, changes.size());
    }
}
