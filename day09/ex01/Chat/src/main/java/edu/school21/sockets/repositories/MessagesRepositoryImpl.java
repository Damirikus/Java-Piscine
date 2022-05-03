package edu.school21.sockets.repositories;

import edu.school21.sockets.models.Message;
import edu.school21.sockets.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@Component
public class MessagesRepositoryImpl implements CrudRepository<Message> {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public MessagesRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public MessagesRepositoryImpl() {
    }

    @Override
    public Optional<Message> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Message> findAll() {
        return null;
    }

    @Override
    public void save(Message entity) {
        String sql = "INSERT INTO message(sender, message, date) VALUES(?, ?, ?)";
        jdbcTemplate.update(sql, entity.getSender().getId(), entity.getMessage(), entity.getDate());
    }

    @Override
    public void update(Message entity) {

    }

    @Override
    public void delete(Long id) {

    }

}
