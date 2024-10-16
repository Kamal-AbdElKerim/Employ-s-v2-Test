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

    @Test
    public void testCalculateFamilyAllowance() {
        
        EmployeFamiliale employeFamiliale = new EmployeFamiliale();
        when(calculateFamilyAllowanceRepo.calculateFamilyAllowance(any(EmployeFamiliale.class))).thenReturn(1500.0);

       
        double result = calculateFamilyAllowanceService.calculateFamilyAllowance(employeFamiliale);

        
        assertEquals(1500.0, result, 0.001);
        verify(calculateFamilyAllowanceRepo, times(1)).calculateFamilyAllowance(any(EmployeFamiliale.class));
    }

    @Test
    public void testGetAllFamilyAllowances() {
        
        EmployeFamiliale emp1 = new EmployeFamiliale();
        EmployeFamiliale emp2 = new EmployeFamiliale();
        List<EmployeFamiliale> employes = Arrays.asList(emp1, emp2);
        when(calculateFamilyAllowanceRepo.getAllFamilyAllowances()).thenReturn(employes);

       
        List<EmployeFamiliale> result = calculateFamilyAllowanceService.getAllFamilyAllowances();

        
        assertEquals(2, result.size());
        verify(calculateFamilyAllowanceRepo, times(1)).getAllFamilyAllowances();
    }

    @Test
    public void testGetFamilyAllowanceByEmployeeId() {
        
        EmployeFamiliale employeFamiliale = new EmployeFamiliale();
        when(calculateFamilyAllowanceRepo.getFamilyAllowanceByEmployeeId(anyLong())).thenReturn(employeFamiliale);

       
        EmployeFamiliale result = calculateFamilyAllowanceService.getFamilyAllowanceByEmployeeId(1L);

        
        assertEquals(employeFamiliale, result);
        verify(calculateFamilyAllowanceRepo, times(1)).getFamilyAllowanceByEmployeeId(1L);
    }

    @Test
    public void testUpdateFamilyAllowance() {
        
        EmployeFamiliale employeFamiliale = new EmployeFamiliale();
        when(calculateFamilyAllowanceRepo.updateFamilyAllowance(anyLong(), any(EmployeFamiliale.class))).thenReturn(true);

       
        boolean result = calculateFamilyAllowanceService.updateFamilyAllowance(1L, employeFamiliale);

        
        assertTrue(result);
        verify(calculateFamilyAllowanceRepo, times(1)).updateFamilyAllowance(1L, employeFamiliale);
    }

    @Test
    public void testGetFamilyAllowancesById() {
        
        EmployeFamiliale employeFamiliale = new EmployeFamiliale();
        when(calculateFamilyAllowanceRepo.getFamilyAllowancesById(anyLong())).thenReturn(employeFamiliale);

       
        EmployeFamiliale result = calculateFamilyAllowanceService.getFamilyAllowancesById(1L);

        
        assertEquals(employeFamiliale, result);
        verify(calculateFamilyAllowanceRepo, times(1)).getFamilyAllowancesById(1L);
    }

    @Test
    public void testCalculateAllowance_LessThan6000() {
       
        double result = calculateFamilyAllowanceService.calculateAllowance(4, 5500);

        
        assertEquals(1050, result, 0.001); // 300 * 3 + 150 * 1
    }

    @Test
    public void testCalculateAllowance_MoreThan8000() {
       
        double result = calculateFamilyAllowanceService.calculateAllowance(4, 8500);

        
        assertEquals(710, result, 0.001); // 200 * 3 + 110 * 1
    }

    @Test
    public void testCalculateAllowance_NoChildren() {
       
        double result = calculateFamilyAllowanceService.calculateAllowance(0, 5500);

        
        assertEquals(0, result, 0.001);
    }

    @Test
    public void testCalculateAllowance_NegativeChildren() {
       
        double result = calculateFamilyAllowanceService.calculateAllowance(-1, 5500);

        
        assertEquals(0, result, 0.001);
    }
}
