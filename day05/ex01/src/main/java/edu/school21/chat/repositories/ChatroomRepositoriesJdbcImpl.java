package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ChatroomRepositoriesJdbcImpl implements ChatroomRepositories{
    @Override
    public Optional<Chatroom> findById(Long id) {
        UsersRepositoriesJdbcImpl usersRepositoriesJdbc = new UsersRepositoriesJdbcImpl();
        Chatroom chatroom = null;
        try {
            String sql = "select * from rooms where chatroom_id=?";

            PreparedStatement pst = DataSource.getConnection().prepareStatement(sql);
            pst.setLong(1, id);
            ResultSet resultSet = pst.executeQuery();
            resultSet.next();

            chatroom = new Chatroom();
            chatroom.setChatroomId(resultSet.getLong("chatroom_id"));
            chatroom.setOwner(usersRepositoriesJdbc.findById(resultSet.getLong("owner")).get());
            chatroom.setName(resultSet.getString("chat_name"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.ofNullable(chatroom);
    }
}
