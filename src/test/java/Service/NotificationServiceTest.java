package Service;

import Repository.NotificationRepo;
import model.Notification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NotificationServiceTest {

    @InjectMocks
    private NotificationService notificationService;

    @Mock
    private NotificationRepo notificationRepo;

    private Notification notification;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        notification = new Notification();
        notification.setNotificationId(1L);
        notification.setMessage("Test Notification");

    }

    @Test
    void testCreateNotification() {
        // Arrange
        when(notificationRepo.createNotification(notification)).thenReturn(notification);

        // Act
        Notification createdNotification = notificationService.createNotification(notification);

        // Assert
        assertEquals(notification, createdNotification);
        verify(notificationRepo, times(1)).createNotification(notification);
    }

    @Test
    void testFindAllNotifications() {
        // Arrange
        when(notificationRepo.findAllNotifications()).thenReturn(Collections.singletonList(notification));

        // Act
        List<Notification> notifications = notificationService.findAllNotifications();

        // Assert
        assertEquals(1, notifications.size());
        assertEquals(notification, notifications.get(0));
    }
}
