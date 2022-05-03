package school21.spring.service.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.stereotype.Component;
import school21.spring.service.config.TestApplicationConfig;
import static org.junit.jupiter.api.Assertions.*;
import javax.sql.DataSource;


public class UsersServiceImplTest {





    @Test
    void signUpTest(){
        ApplicationContext context = new AnnotationConfigApplicationContext(TestApplicationConfig.class);
        UsersServiceImpl usersService = context.getBean(UsersServiceImpl.class);
        assertNotNull(usersService.signUp("lol"));
    }

}
