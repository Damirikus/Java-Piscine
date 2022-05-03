package edu.school21.sockets.services;


import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class UsersServiceImpl implements UsersService{


    PasswordEncoder passwordEncoder;
    UsersRepository usersRepository;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UsersServiceImpl() {
    }

    @Override
    public String signUp(String username, String password) {
        User user = new User(username, passwordEncoder.encode(password));
        usersRepository.save(user);
        return "Successful!";
    }
}
