package Service;

import Repository.AdminRepo;
import model.Auth.Admin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class AdminServiceTest {

    @InjectMocks
    private AdminService adminService;

    @Mock
    private AdminRepo adminRepo;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test for findAdminByEmail()
    @Test
    public void testFindAdminByEmail_Found() {
        // Arrange
        Admin admin = new Admin();
        admin.setEmail("admin@example.com");
        when(adminRepo.findAdminByEmail("admin@example.com")).thenReturn(admin);

        // Act
        Admin result = adminService.findAdminByEmail("admin@example.com");

        // Assert
        assertEquals(admin, result);
        verify(adminRepo, times(1)).findAdminByEmail("admin@example.com");
    }

    @Test
    public void testFindAdminByEmail_NotFound() {
        // Arrange
        when(adminRepo.findAdminByEmail("admin@example.com")).thenReturn(null);

        // Act
        Admin result = adminService.findAdminByEmail("admin@example.com");

        // Assert
        assertNull(result);
        verify(adminRepo, times(1)).findAdminByEmail("admin@example.com");
    }
}
