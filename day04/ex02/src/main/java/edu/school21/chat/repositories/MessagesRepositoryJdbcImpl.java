package edu.school21.chat.repositories;


import edu.school21.chat.models.Message;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepositories {
    UsersRepositories usersRepositories = new UsersRepositoriesJdbcImpl();
    ChatroomRepositories chatroomRepositories = new ChatroomRepositoriesJdbcImpl();

    @Override
    public Optional<Message> findById(Long id) {

        Message message = null;
        try {
            String sql = "select * from messages where message_id=?";

             PreparedStatement pst = DataSource.getConnection().prepareStatement(sql);
             pst.setLong(1, id);
             ResultSet resultSet = pst.executeQuery();
             resultSet.next();

             message = new Message();
             message.setMessageId(resultSet.getLong("message_id"));
             usersRepositories.findById(resultSet.getLong("author")).ifPresent(message::setAuthor);
             chatroomRepositories.findById(resultSet.getLong("room")).ifPresent(message::setRoom);
             message.setText(resultSet.getString("message"));
            message.setDate(resultSet.getTimestamp("send_date").toLocalDateTime());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.ofNullable(message);
    }

    @Override
    public void save(Message message) {
        usersRepositories.findById(message.getAuthor().getUserId()).orElseThrow(NotSavedSubEntityException::new);
        usersRepositories.findById(message.getRoom().getChatroomId()).orElseThrow(NotSavedSubEntityException::new);

        try {
            String sql = "INSERT INTO messages(author, room, message, send_date) VALUES(?, ?, ?, ?)";
            PreparedStatement statement = DataSource.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, message.getAuthor().getUserId());
            statement.setLong(2, message.getRoom().getChatroomId());
            statement.setString(3, message.getText());
            statement.setTimestamp(4, Timestamp.valueOf(message.getDate()));

            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            message.setMessageId(resultSet.getLong("message_id"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public static class NotSavedSubEntityException extends RuntimeException {
        public NotSavedSubEntityException(){
        }
    }
}
