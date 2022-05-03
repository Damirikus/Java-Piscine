package edu.school21.chat.repositories;

import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
            System.out.println("User not found!");
            return Optional.empty();
        }
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll(int page, int size) {
        List<User> users = new ArrayList<>();
        try {
            String sql = "SELECT * FROM users OFFSET ?*? LIMIT ?";

            PreparedStatement pst = DataSource.getConnection().prepareStatement(sql);
            pst.setInt(1, page);
            pst.setInt(2, size);
            pst.setInt(3, size);

            ResultSet resultSet = pst.executeQuery();
            if (!resultSet.next())
                return null;
            while (resultSet.next()){
                User user = new User();
                user.setUserId(resultSet.getLong("user_id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                users.add(user);
            }


        } catch (SQLException throwables) {
            System.out.println("Users not found!");
            return null;
        }
        return users;
    }
}
