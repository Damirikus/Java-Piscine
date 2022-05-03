package edu.school21.chat.repositories;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepositories {


    @Override
    public Optional<Message> findById(Long id) {
        UsersRepositoriesJdbcImpl usersRepositoriesJdbc = new UsersRepositoriesJdbcImpl();
        ChatroomRepositoriesJdbcImpl chatroomRepositoriesJdbc = new ChatroomRepositoriesJdbcImpl();
        Message message = null;
        try {
            String sql = "select * from messages where message_id=?";

             PreparedStatement pst = DataSource.getConnection().prepareStatement(sql);
             pst.setLong(1, id);
             ResultSet resultSet = pst.executeQuery();
             resultSet.next();

             message = new Message();
             message.setMessageId(resultSet.getLong("message_id"));
             message.setAuthor(usersRepositoriesJdbc.findById(resultSet.getLong("author")).get());
             message.setRoom(chatroomRepositoriesJdbc.findById(resultSet.getLong("room")).get());
             message.setText(resultSet.getString("message"));
            message.setDate(resultSet.getTimestamp("send_date").toLocalDateTime());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.ofNullable(message);
    }
}
