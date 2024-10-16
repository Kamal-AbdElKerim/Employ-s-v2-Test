package Service;

import Repository.CongeRepo;
import model.Conge;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CongeServiceTest {

    @InjectMocks
    private CongeService congeService;

    @Mock
    private CongeRepo congeRepo;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateConge() {
        
        Conge conge = new Conge();
        byte[] pdfData = new byte[]{1, 2, 3};

        doNothing().when(congeRepo).createConge(conge, pdfData);

      
        congeService.createConge(conge, pdfData);

        
        verify(congeRepo, times(1)).createConge(conge, pdfData);
    }

    @Test
    public void testGetAllCongesByEmployee() {
        
        long employeeId = 1L;
        List<Conge> expectedConges = Arrays.asList(new Conge(), new Conge());
        when(congeRepo.getAllCongesByEmployee(employeeId)).thenReturn(expectedConges);

      
        List<Conge> result = congeService.getAllCongesByEmployee(employeeId);

        
        assertEquals(expectedConges, result);
        verify(congeRepo, times(1)).getAllCongesByEmployee(employeeId);
    }

    @Test
    public void testGetApprovedCongesByEmployeeId() {
        
        long employeeId = 1L;
        List<Conge> expectedConges = Arrays.asList(new Conge(), new Conge());
        when(congeRepo.getApprovedCongesByEmployeeId(employeeId)).thenReturn(expectedConges);

      
        List<Conge> result = congeService.getApprovedCongesByEmployeeId(employeeId);

        
        assertEquals(expectedConges, result);
        verify(congeRepo, times(1)).getApprovedCongesByEmployeeId(employeeId);
    }

    @Test
    public void testGetApprovedConges() {
        
        List<Conge> expectedConges = Arrays.asList(new Conge(), new Conge());
        when(congeRepo.getApprovedConges()).thenReturn(expectedConges);

      
        List<Conge> result = congeService.getApprovedConges();

        
        assertEquals(expectedConges, result);
        verify(congeRepo, times(1)).getApprovedConges();
    }

    @Test
    public void testGetAllConges() {
        
        List<Conge> expectedConges = Arrays.asList(new Conge(), new Conge());
        when(congeRepo.getAllConges()).thenReturn(expectedConges);

      
        List<Conge> result = congeService.getAllConges();

        
        assertEquals(expectedConges, result);
        verify(congeRepo, times(1)).getAllConges();
    }

    @Test
    public void testGetCongeById() {
        
        Long congeId = 1L;
        Conge conge = new Conge();
        when(congeRepo.getCongeById(congeId)).thenReturn(conge);

      
        Conge result = congeService.getCongeById(congeId);

        
        assertEquals(conge, result);
        verify(congeRepo, times(1)).getCongeById(congeId);
    }

    @Test
    public void testUpdateConge() {
        
        Conge conge = new Conge();
        doNothing().when(congeRepo).updateConge(conge);

      
        congeService.updateConge(conge);

        
        verify(congeRepo, times(1)).updateConge(conge);
    }

    @Test
    public void testDeleteConge() {
        
        Long congeId = 1L;
        doNothing().when(congeRepo).deleteConge(congeId);

      
        congeService.deleteConge(congeId);

        
        verify(congeRepo, times(1)).deleteConge(congeId);
    }

    @Test
    public void testDejaConge() throws Exception {
        
        Date dateDebut = new Date();
        Date dateFin = new Date(System.currentTimeMillis() + 86400000); // 1 day later
        List<Conge> approvedConges = Arrays.asList(new Conge(dateDebut, dateFin));

        // Create a mock RequestDispatcher
        RequestDispatcher mockDispatcher = mock(RequestDispatcher.class);

        // Mock the behavior of request and response
        when(request.getRequestDispatcher("EmployeeDashboard/AddConges.jsp")).thenReturn(mockDispatcher);

      
        congeService.DejaConge(request, response, dateDebut, dateFin, approvedConges);

        
        verify(request, times(1)).setAttribute("errorMessage", "Les dates demandées chevauchent des congés déjà approuvés.");
        verify(mockDispatcher, times(1)).forward(request, response);
    }


    @Test
    public void testChackSoldeConges() throws Exception {
        
        long numberOfDaysRequested = 15;
        long soldeConges = 10;

        // Create a mock RequestDispatcher
        RequestDispatcher mockDispatcher = mock(RequestDispatcher.class);

        // Mock the behavior of request and response
        when(request.getRequestDispatcher("EmployeeDashboard/AddConges.jsp")).thenReturn(mockDispatcher);

      
        congeService.ChackSoldeConges(request, response, numberOfDaysRequested, soldeConges);

        
        verify(request, times(1)).setAttribute("errorMessage", "Le nombre de jours demandés dépasse le solde de congés (10 jour)  disponible.");
        verify(mockDispatcher, times(1)).forward(request, response);
    }


}