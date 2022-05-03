package edu.school21.sockets.repositories;

import edu.school21.sockets.models.User;

import java.util.List;
import java.util.Optional;

public interface CrudRepository {
    Optional<User> findById(Long id);
    List<User> findAll();
    void save(User entity);
    void update(User entity);
    void delete(Long id);
}
