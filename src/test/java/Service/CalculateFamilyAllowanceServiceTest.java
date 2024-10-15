package Service;

import Repository.CalculateFamilyAllowanceRepo;
import model.EmployeFamiliale;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CalculateFamilyAllowanceServiceTest {

    @InjectMocks
    private CalculateFamilyAllowanceService calculateFamilyAllowanceService;

    @Mock
    private CalculateFamilyAllowanceRepo calculateFamilyAllowanceRepo;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test for calculateFamilyAllowance()
    @Test
    public void testCalculateFamilyAllowance() {
        // Arrange
        EmployeFamiliale employeFamiliale = new EmployeFamiliale();
        when(calculateFamilyAllowanceRepo.calculateFamilyAllowance(any(EmployeFamiliale.class))).thenReturn(1500.0);

        // Act
        double result = calculateFamilyAllowanceService.calculateFamilyAllowance(employeFamiliale);

        // Assert
        assertEquals(1500.0, result, 0.001);
        verify(calculateFamilyAllowanceRepo, times(1)).calculateFamilyAllowance(any(EmployeFamiliale.class));
    }

    // Test for getAllFamilyAllowances()
    @Test
    public void testGetAllFamilyAllowances() {
        // Arrange
        EmployeFamiliale emp1 = new EmployeFamiliale();
        EmployeFamiliale emp2 = new EmployeFamiliale();
        List<EmployeFamiliale> employes = Arrays.asList(emp1, emp2);
        when(calculateFamilyAllowanceRepo.getAllFamilyAllowances()).thenReturn(employes);

        // Act
        List<EmployeFamiliale> result = calculateFamilyAllowanceService.getAllFamilyAllowances();

        // Assert
        assertEquals(2, result.size());
        verify(calculateFamilyAllowanceRepo, times(1)).getAllFamilyAllowances();
    }

    // Test for getFamilyAllowanceByEmployeeId()
    @Test
    public void testGetFamilyAllowanceByEmployeeId() {
        // Arrange
        EmployeFamiliale employeFamiliale = new EmployeFamiliale();
        when(calculateFamilyAllowanceRepo.getFamilyAllowanceByEmployeeId(anyLong())).thenReturn(employeFamiliale);

        // Act
        EmployeFamiliale result = calculateFamilyAllowanceService.getFamilyAllowanceByEmployeeId(1L);

        // Assert
        assertEquals(employeFamiliale, result);
        verify(calculateFamilyAllowanceRepo, times(1)).getFamilyAllowanceByEmployeeId(1L);
    }

    // Test for updateFamilyAllowance()
    @Test
    public void testUpdateFamilyAllowance() {
        // Arrange
        EmployeFamiliale employeFamiliale = new EmployeFamiliale();
        when(calculateFamilyAllowanceRepo.updateFamilyAllowance(anyLong(), any(EmployeFamiliale.class))).thenReturn(true);

        // Act
        boolean result = calculateFamilyAllowanceService.updateFamilyAllowance(1L, employeFamiliale);

        // Assert
        assertTrue(result);
        verify(calculateFamilyAllowanceRepo, times(1)).updateFamilyAllowance(1L, employeFamiliale);
    }

    // Test for getFamilyAllowancesById()
    @Test
    public void testGetFamilyAllowancesById() {
        // Arrange
        EmployeFamiliale employeFamiliale = new EmployeFamiliale();
        when(calculateFamilyAllowanceRepo.getFamilyAllowancesById(anyLong())).thenReturn(employeFamiliale);

        // Act
        EmployeFamiliale result = calculateFamilyAllowanceService.getFamilyAllowancesById(1L);

        // Assert
        assertEquals(employeFamiliale, result);
        verify(calculateFamilyAllowanceRepo, times(1)).getFamilyAllowancesById(1L);
    }

    // Test for calculateAllowance() method with different conditions
    @Test
    public void testCalculateAllowance_LessThan6000() {
        // Act
        double result = calculateFamilyAllowanceService.calculateAllowance(4, 5500);

        // Assert
        assertEquals(1050, result, 0.001); // 300 * 3 + 150 * 1
    }

    @Test
    public void testCalculateAllowance_MoreThan8000() {
        // Act
        double result = calculateFamilyAllowanceService.calculateAllowance(4, 8500);

        // Assert
        assertEquals(710, result, 0.001); // 200 * 3 + 110 * 1
    }

    @Test
    public void testCalculateAllowance_NoChildren() {
        // Act
        double result = calculateFamilyAllowanceService.calculateAllowance(0, 5500);

        // Assert
        assertEquals(0, result, 0.001);
    }

    @Test
    public void testCalculateAllowance_NegativeChildren() {
        // Act
        double result = calculateFamilyAllowanceService.calculateAllowance(-1, 5500);

        // Assert
        assertEquals(0, result, 0.001);
    }
}
