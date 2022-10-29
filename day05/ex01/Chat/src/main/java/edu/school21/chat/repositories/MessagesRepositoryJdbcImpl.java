package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    private DataSource dataSource;
    private static final String FIND_BY_ID_SQL = new StringBuilder()
            .append("SELECT message_id, message, message_date, ")
            .append("user_id, user_login, user_pass, ")
            .append("chatrooms.chatroom_id, chatroom_name ")
            .append("FROM chatrooms ")
            .append("INNER JOIN messages USING (chatroom_id) ")
            .append("INNER JOIN users ON author_id = user_id ")
            .append("WHERE message_id = ?").toString();

    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Message> findById(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            Message message = null;
            if (resultSet.next()) {
                message = buildMessage(resultSet);
            }

            return Optional.ofNullable(message);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Message buildMessage(ResultSet resultSet) throws SQLException {
        Message message = new Message(
                resultSet.getLong("message_id"),
                new User(
                        resultSet.getLong("user_id"),
                        resultSet.getString("user_login"),
                        resultSet.getString("user_pass")
                ),
                new Chatroom(
                        resultSet.getLong("chatroom_id"),
                        resultSet.getString("chatroom_name")
                ),
                resultSet.getString("message"),
                resultSet.getTimestamp("message_date") == null ?
                        null : resultSet.getTimestamp("message_date").toLocalDateTime()
        );
        return message;
    }

}
