package edu.school21.sockets.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import edu.school21.sockets.models.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@Component
public class UsersRepositoryImpl implements UsersRepository {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public UsersRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public UsersRepositoryImpl() {
    }

    @Override
    public Optional<User> findById(Long id) {
        String sql = "select * from users where id=?";
        return jdbcTemplate.query(sql, new Object[]{id}, new UserMapper()).stream().findAny();
    }

    @Override
    public List<User> findAll() {
        String sql = "select * from users";
        return jdbcTemplate.query(sql, new UserMapper());
    }

    @Override
    public void save(User entity) {
        String sql = "INSERT INTO users(username, password) VALUES(?, ?)";
        jdbcTemplate.update(sql, entity.getUsername(), entity.getPassword().toString());
    }

    @Override
    public void update(User entity) {
        String sql = "UPDATE users SET username=?, password=? WHERE id=?";
        jdbcTemplate.update(sql, entity.getUsername(),entity.getPassword() , entity.getId());
    }

    @Override
    public void delete(Long id) {
        String sql = "delete from users where id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        String sql = "select * from users where username=?";
        return jdbcTemplate.query(sql, new Object[]{username}, new UserMapper()).stream().findAny();
    }


    public static class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            return user;
        }
    }
}
