package Service;

import Repository.CandidatureRepo;
import model.Candidature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CandidatureServiceTest {

    @InjectMocks
    private CandidatureService candidatureService;

    @Mock
    private CandidatureRepo candidatureRepo;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test for isEmailUniqueForOffre()
    @Test
    public void testIsEmailUniqueForOffre() {
        // Arrange
        String email = "test@example.com";
        long offreId = 1L;
        when(candidatureRepo.isEmailUniqueForOffre(email, offreId)).thenReturn(true);

        // Act
        boolean result = candidatureService.isEmailUniqueForOffre(email, offreId);

        // Assert
        assertTrue(result);
        verify(candidatureRepo, times(1)).isEmailUniqueForOffre(email, offreId);
    }

    // Test for addCandidature()
    @Test
    public void testAddCandidature() {
        // Arrange
        Candidature candidature = new Candidature();
        doNothing().when(candidatureRepo).addCandidature(candidature);

        // Act
        candidatureService.addCandidature(candidature);

        // Assert
        verify(candidatureRepo, times(1)).addCandidature(candidature);
    }

    // Test for getCandidatureById()
    @Test
    public void testGetCandidatureById() {
        // Arrange
        Long id = 1L;
        Candidature candidature = new Candidature();
        when(candidatureRepo.getCandidatureById(id)).thenReturn(candidature);

        // Act
        Candidature result = candidatureService.getCandidatureById(id);

        // Assert
        assertEquals(candidature, result);
        verify(candidatureRepo, times(1)).getCandidatureById(id);
    }

    // Test for getAllCandidatures()
    @Test
    public void testGetAllCandidatures() {
        // Arrange
        List<Candidature> candidatures = Arrays.asList(new Candidature(), new Candidature());
        when(candidatureRepo.getAllCandidatures()).thenReturn(candidatures);

        // Act
        List<Candidature> result = candidatureService.getAllCandidatures();

        // Assert
        assertEquals(candidatures, result);
        verify(candidatureRepo, times(1)).getAllCandidatures();
    }

    // Test for acceptCandidature()
    @Test
    public void testAcceptCandidature() {
        // Arrange
        Long id = 1L;
        doNothing().when(candidatureRepo).acceptCandidature(id);

        // Act
        candidatureService.acceptCandidature(id);

        // Assert
        verify(candidatureRepo, times(1)).acceptCandidature(id);
    }

    // Test for rejectCandidature()
    @Test
    public void testRejectCandidature() {
        // Arrange
        Long id = 1L;
        doNothing().when(candidatureRepo).rejectCandidature(id);

        // Act
        candidatureService.rejectCandidature(id);

        // Assert
        verify(candidatureRepo, times(1)).rejectCandidature(id);
    }

    // Test for filterBySkills()
    @Test
    public void testFilterBySkills() {
        // Arrange
        List<String> skills = Arrays.asList("Java", "Spring");
        List<Candidature> candidatures = Arrays.asList(new Candidature(), new Candidature());
        when(candidatureRepo.filterBySkills(skills)).thenReturn(candidatures);

        // Act
        List<Candidature> result = candidatureService.filterBySkills(skills);

        // Assert
        assertEquals(candidatures, result);
        verify(candidatureRepo, times(1)).filterBySkills(skills);
    }
}
