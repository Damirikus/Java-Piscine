package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;

public class UsersServiceImplTest {

    @Mock
    UsersRepository usersRepository;
    UsersServiceImpl usersService;

    public UsersServiceImplTest() {
        MockitoAnnotations.initMocks(this);
        this.usersService = new UsersServiceImpl(usersRepository);

    }

    @Test
    void authenticateTestWhenTrue(){
        User user = new User(1L, "login", "pwd", true);
        Mockito.when(usersRepository.findByLogin("login")).thenReturn(user);
        assertThrows(AlreadyAuthenticatedException.class, ()-> usersService.authenticate("login", "pwd"));
    }

    @Test
    void authenticateTestWhenFalseAndEquals(){
        User user = new User(1L, "login", "pwd", false);
        User userTrue = new User(1L, "login", "pwd", true);

        Mockito.when(usersRepository.findByLogin(user.getLogin())).thenReturn(user);
        assertTrue(usersService.authenticate(user.getLogin(), user.getPassword()));
        Mockito.verify(usersRepository).update(userTrue);

    }

    @Test
    void authenticateTestWhenFalseAndNotEquals(){
        User user = new User(1L, "login", "pwd", false);

        Mockito.when(usersRepository.findByLogin(user.getLogin())).thenReturn(user);
        assertFalse(usersService.authenticate(user.getLogin(), "another"));

    }

    @Test
    void authenticateTestWhenUserNull(){
        User user = new User(1L, "login", "pwd", false);

        Mockito.when(usersRepository.findByLogin(user.getLogin())).thenReturn(null);
        assertFalse(usersService.authenticate(user.getLogin(), "another"));

    }

}
