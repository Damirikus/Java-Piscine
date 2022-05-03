package school21.spring.service.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository<User>{
    JdbcTemplate jdbcTemplate;

    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
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
        String sql = "INSERT INTO users(email) VALUES(?)";
        jdbcTemplate.update(sql, entity.getEmail());
    }

    @Override
    public void update(User entity) {
        String sql = "UPDATE users SET email=? WHERE id=?";
        jdbcTemplate.update(sql, entity.getEmail(), entity.getId());
    }

    @Override
    public void delete(Long id) {
        String sql = "delete from users where id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String sql = "select * from users where email=?";
        return jdbcTemplate.query(sql, new Object[]{email}, new UserMapper()).stream().findAny();
    }


    public static class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setEmail(rs.getString("email"));
            return user;
        }
    }
}
