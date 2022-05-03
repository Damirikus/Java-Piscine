package edu.school21.chat.repositories;

import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UsersRepositoriesJdbcImpl implements UsersRepositories{
    @Override
    public Optional<User> findById(Long id) {

        User user = null;
        try {
            String sql = "select * from users where user_id=?";

            PreparedStatement pst = DataSource.getConnection().prepareStatement(sql);
            pst.setLong(1, id);
            ResultSet resultSet = pst.executeQuery();
            resultSet.next();

            user = new User();
            user.setUserId(resultSet.getLong("user_id"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.ofNullable(user);
    }
}
