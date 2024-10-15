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

    // Test for createConge()
    @Test
    public void testCreateConge() {
        // Arrange
        Conge conge = new Conge();
        byte[] pdfData = new byte[]{1, 2, 3};

        doNothing().when(congeRepo).createConge(conge, pdfData);

        // Act
        congeService.createConge(conge, pdfData);

        // Assert
        verify(congeRepo, times(1)).createConge(conge, pdfData);
    }

    // Test for getAllCongesByEmployee()
    @Test
    public void testGetAllCongesByEmployee() {
        // Arrange
        long employeeId = 1L;
        List<Conge> expectedConges = Arrays.asList(new Conge(), new Conge());
        when(congeRepo.getAllCongesByEmployee(employeeId)).thenReturn(expectedConges);

        // Act
        List<Conge> result = congeService.getAllCongesByEmployee(employeeId);

        // Assert
        assertEquals(expectedConges, result);
        verify(congeRepo, times(1)).getAllCongesByEmployee(employeeId);
    }

    // Test for getApprovedCongesByEmployeeId()
    @Test
    public void testGetApprovedCongesByEmployeeId() {
        // Arrange
        long employeeId = 1L;
        List<Conge> expectedConges = Arrays.asList(new Conge(), new Conge());
        when(congeRepo.getApprovedCongesByEmployeeId(employeeId)).thenReturn(expectedConges);

        // Act
        List<Conge> result = congeService.getApprovedCongesByEmployeeId(employeeId);

        // Assert
        assertEquals(expectedConges, result);
        verify(congeRepo, times(1)).getApprovedCongesByEmployeeId(employeeId);
    }

    // Test for getApprovedConges()
    @Test
    public void testGetApprovedConges() {
        // Arrange
        List<Conge> expectedConges = Arrays.asList(new Conge(), new Conge());
        when(congeRepo.getApprovedConges()).thenReturn(expectedConges);

        // Act
        List<Conge> result = congeService.getApprovedConges();

        // Assert
        assertEquals(expectedConges, result);
        verify(congeRepo, times(1)).getApprovedConges();
    }

    // Test for getAllConges()
    @Test
    public void testGetAllConges() {
        // Arrange
        List<Conge> expectedConges = Arrays.asList(new Conge(), new Conge());
        when(congeRepo.getAllConges()).thenReturn(expectedConges);

        // Act
        List<Conge> result = congeService.getAllConges();

        // Assert
        assertEquals(expectedConges, result);
        verify(congeRepo, times(1)).getAllConges();
    }

    // Test for getCongeById()
    @Test
    public void testGetCongeById() {
        // Arrange
        Long congeId = 1L;
        Conge conge = new Conge();
        when(congeRepo.getCongeById(congeId)).thenReturn(conge);

        // Act
        Conge result = congeService.getCongeById(congeId);

        // Assert
        assertEquals(conge, result);
        verify(congeRepo, times(1)).getCongeById(congeId);
    }

    // Test for updateConge()
    @Test
    public void testUpdateConge() {
        // Arrange
        Conge conge = new Conge();
        doNothing().when(congeRepo).updateConge(conge);

        // Act
        congeService.updateConge(conge);

        // Assert
        verify(congeRepo, times(1)).updateConge(conge);
    }

    // Test for deleteConge()
    @Test
    public void testDeleteConge() {
        // Arrange
        Long congeId = 1L;
        doNothing().when(congeRepo).deleteConge(congeId);

        // Act
        congeService.deleteConge(congeId);

        // Assert
        verify(congeRepo, times(1)).deleteConge(congeId);
    }

    // Test for DejaConge()
    @Test
    public void testDejaConge() throws Exception {
        // Arrange
        Date dateDebut = new Date();
        Date dateFin = new Date(System.currentTimeMillis() + 86400000); // 1 day later
        List<Conge> approvedConges = Arrays.asList(new Conge(dateDebut, dateFin));

        // Create a mock RequestDispatcher
        RequestDispatcher mockDispatcher = mock(RequestDispatcher.class);

        // Mock the behavior of request and response
        when(request.getRequestDispatcher("EmployeeDashboard/AddConges.jsp")).thenReturn(mockDispatcher);

        // Act
        congeService.DejaConge(request, response, dateDebut, dateFin, approvedConges);

        // Assert
        verify(request, times(1)).setAttribute("errorMessage", "Les dates demandées chevauchent des congés déjà approuvés.");
        verify(mockDispatcher, times(1)).forward(request, response);
    }


    @Test
    public void testChackSoldeConges() throws Exception {
        // Arrange
        long numberOfDaysRequested = 15;
        long soldeConges = 10;

        // Create a mock RequestDispatcher
        RequestDispatcher mockDispatcher = mock(RequestDispatcher.class);

        // Mock the behavior of request and response
        when(request.getRequestDispatcher("EmployeeDashboard/AddConges.jsp")).thenReturn(mockDispatcher);

        // Act
        congeService.ChackSoldeConges(request, response, numberOfDaysRequested, soldeConges);

        // Assert
        verify(request, times(1)).setAttribute("errorMessage", "Le nombre de jours demandés dépasse le solde de congés (10 jour)  disponible.");
        verify(mockDispatcher, times(1)).forward(request, response);
    }


}