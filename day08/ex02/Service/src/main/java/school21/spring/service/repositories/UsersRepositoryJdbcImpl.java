package school21.spring.service.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Component
public class UsersRepositoryJdbcImpl implements UsersRepository<User>{
    DataSource dataSource;

    @Autowired
    public UsersRepositoryJdbcImpl(@Qualifier("driverManagerBean") DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public UsersRepositoryJdbcImpl() {
    }

    @Override
    public Optional<User> findById(Long id) {
        User user = null;
        try {
            String sql = "select * from users where id=?";

            PreparedStatement pst = dataSource.getConnection().prepareStatement(sql);
            pst.setLong(1, id);
            ResultSet resultSet = pst.executeQuery();

            if (!resultSet.next()) {
                return Optional.empty();
            }
            user = new User();
            user.setId(resultSet.getLong("id"));
            user.setEmail(resultSet.getString("email"));
        } catch (SQLException t) { return Optional.empty(); }
        return Optional.of(user);
    }

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        try {
            String sql = "select * from users";

            PreparedStatement pst = dataSource.getConnection().prepareStatement(sql);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setEmail(resultSet.getString("email"));
                list.add(user);
            }
        } catch (SQLException throwables) {
            System.out.println("Product not found!");
            return null;
        }
        return list;
    }

    @Override
    public void save(User entity) {
        try {
            String sql = "INSERT INTO users(email) VALUES(?)";
            PreparedStatement statement = dataSource.getConnection().prepareStatement(sql);
            statement.setString(1, entity.getEmail());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(User entity) {
        try {
            String sql = "UPDATE users SET email=? WHERE id=?";
            PreparedStatement statement = dataSource.getConnection().prepareStatement(sql);
            statement.setString(1, entity.getEmail());
            statement.setLong(2, entity.getId());
            statement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try {
            String sql = "delete from users where id=?";

            PreparedStatement pst = dataSource.getConnection().prepareStatement(sql);
            pst.setLong(1, id);
            pst.executeQuery();

        } catch (SQLException throwables) {
            System.out.println("Product not found!");
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        User user = null;
        try {
            String sql = "select * from users where email=?";

            PreparedStatement pst = dataSource.getConnection().prepareStatement(sql);
            pst.setString(1, email);
            ResultSet resultSet = pst.executeQuery();
            if(!resultSet.next()){
                return Optional.empty();
            }
            user = new User();
            user.setId(resultSet.getLong("id"));
            user.setEmail(resultSet.getString("email"));
        } catch (SQLException t) { return Optional.empty(); }
        return Optional.of(user);
    }


}
