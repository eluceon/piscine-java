package school21.spring.service.services;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import school21.spring.service.config.TestApplicationConﬁg;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class UsersServiceImplTest {
    @Test
    void UsersServicesTest() {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(TestApplicationConﬁg.class);
        UsersService userService = context.getBean(UsersService.class);
        String password = userService.signUp("eluceon@email.ru");
        assertNotNull(password);
        context.close();
    }
}