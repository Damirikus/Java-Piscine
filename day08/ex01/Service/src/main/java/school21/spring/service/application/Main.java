package school21.spring.service.application;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;
import school21.spring.service.repositories.UsersRepositoryJdbcTemplateImpl;


public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        UsersRepository usersRepository = context.getBean("usersRepositoryJdbc", UsersRepository.class);
        User user = new User("mail.com");
        usersRepository.save(user);
        System.out.println(usersRepository.findById(2L));


        UsersRepositoryJdbcTemplateImpl template = context.getBean("usersRepositoryJdbcTemplate", UsersRepositoryJdbcTemplateImpl.class);

        template.delete(3L);
        System.out.println(template.findAll());

    }
}
