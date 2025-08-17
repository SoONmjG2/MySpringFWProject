package mylab.notification.di.annot;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
//Java Config 클래스 지정
@ContextConfiguration(classes = NotificationConfig.class)
class NotificationConfigTest {

    @Autowired
    private NotificationManager notificationManager;

    @Test
    void testNotificationManager() {
        //매니저 주입 
        assertNotNull(notificationManager);

        //이메일 서비스 검증 
        assertNotNull(notificationManager.getEmailService());
        EmailNotificationService email =
                (EmailNotificationService) notificationManager.getEmailService();
        assertEquals("smtp.gmail.com", email.getSmtpServer());
        assertEquals(587, email.getPort());

        //SMS 서비스 검증
        assertNotNull(notificationManager.getSmsService());
        SmsNotificationService sms =
                (SmsNotificationService) notificationManager.getSmsService();
        assertEquals("SKT", sms.getProvider());

        //동작 확인 (정상만 콘솔 출력)
        notificationManager.sendNotificationByEmail("테스트 이메일");
        notificationManager.sendNotificationBySms("테스트 SMS");
    }
}
