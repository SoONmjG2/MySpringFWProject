package mylab.user.di.annot;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import mylab.user.di.annot.UserService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:mylab-user-di.xml")
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void testUserService() {

        assertNotNull(userService, "userService 빈 주입 실패");
        assertNotNull(userService.getUserRepository(), "userRepository 주입 실패");
        assertNotNull(userService.getSecurityService(), "securityService 주입 실패");


        assertEquals("MySQL", userService.getUserRepository().getDbType());

        assertTrue(userService.registerUser("hong", "홍길동", "pw123"));
    }
}
