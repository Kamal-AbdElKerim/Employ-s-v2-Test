package Service;

import Repository.OffreRepo;
import model.OffreEmploi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OffreServiceTest {

    @InjectMocks
    private OffreService offreService;

    @Mock
    private OffreRepo offreRepo;

    private OffreEmploi offre;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        offre = new OffreEmploi();
        offre.setOffreId(1L); // Assuming your OffreEmploi class has an ID field
        offre.setDescription("Test Job Offer");

    }

    @Test
    void testCreateOffre() {
        // Arrange
        when(offreRepo.createOffre(offre)).thenReturn(true);

        // Act
        boolean result = offreService.createOffre(offre);

        // Assert
        assertTrue(result);
        verify(offreRepo, times(1)).createOffre(offre);
    }

    @Test
    void testFindOffreById() {
        // Arrange
        when(offreRepo.findOffreById(1L)).thenReturn(offre);

        // Act
        OffreEmploi foundOffre = offreService.findOffreById(1L);

        // Assert
        assertEquals(offre, foundOffre);
        verify(offreRepo, times(1)).findOffreById(1L);
    }

    @Test
    void testGetAllOffres() {
        // Arrange
        when(offreRepo.getAllOffres()).thenReturn(Arrays.asList(offre));

        // Act
        List<OffreEmploi> offres = offreService.getAllOffres();

        // Assert
        assertEquals(1, offres.size());
        assertEquals(offre, offres.get(0));
    }

    @Test
    void testDeleteOffre() {
        // Arrange
        when(offreRepo.deleteOffre(1L)).thenReturn(true);

        // Act
        boolean result = offreService.deleteOffre(1L);

        // Assert
        assertTrue(result);
        verify(offreRepo, times(1)).deleteOffre(1L);
    }
}
