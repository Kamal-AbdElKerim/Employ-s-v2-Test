package Service;

import Repository.RHRepo;
import model.Auth.RH;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RHServiceTest {

    @InjectMocks
    private RHService rhService;

    @Mock
    private RHRepo rhRepo;

    private RH rh;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        rh = new RH();
        rh.setId(1L);
        rh.setEmail("rh@example.com");

    }

    @Test
    void testFindRHByEmail() {
        // Arrange
        when(rhRepo.findRHByEmail("rh@example.com")).thenReturn(rh);

        // Act
        RH foundRH = rhService.findRHByEmail("rh@example.com");

        // Assert
        assertEquals(rh, foundRH);
        verify(rhRepo, times(1)).findRHByEmail("rh@example.com");
    }

    @Test
    void testFindRHByEmail_NotFound() {
        // Arrange
        when(rhRepo.findRHByEmail("notfound@example.com")).thenReturn(null);

        // Act
        RH foundRH = rhService.findRHByEmail("notfound@example.com");

        // Assert
        assertNull(foundRH);
        verify(rhRepo, times(1)).findRHByEmail("notfound@example.com");
    }
}
