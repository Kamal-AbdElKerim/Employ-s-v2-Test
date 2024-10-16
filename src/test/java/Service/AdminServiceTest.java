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

    
    @Test
    public void testFindAdminByEmail_Found() {
        
        Admin admin = new Admin();
        admin.setEmail("admin@example.com");
        when(adminRepo.findAdminByEmail("admin@example.com")).thenReturn(admin);

        
        Admin result = adminService.findAdminByEmail("admin@example.com");

        
        assertEquals(admin, result);
        verify(adminRepo, times(1)).findAdminByEmail("admin@example.com");
    }

    @Test
    public void testFindAdminByEmail_NotFound() {
        
        when(adminRepo.findAdminByEmail("admin@example.com")).thenReturn(null);

        
        Admin result = adminService.findAdminByEmail("admin@example.com");

        
        assertNull(result);
        verify(adminRepo, times(1)).findAdminByEmail("admin@example.com");
    }
}
