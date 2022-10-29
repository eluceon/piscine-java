package edu.school21.chat.repositories;

import edu.school21.chat.exception.NotSavedSubEntityException;
import edu.school21.chat.exception.RepositoryException;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    private static final String UPDATE_USER_SQL = new StringBuilder()
            .append("UPDATE users ")
            .append("SET user_login = ?, ")
            .append("user_pass = ? ")
            .append("WHERE user_id = ?").toString();

    private static final String UPDATE_CHATROOM_SQL = new StringBuilder()
            .append("UPDATE chatrooms ")
            .append("SET chatroom_name = ?, ")
            .append("owner_id = ? ")
            .append("WHERE chatroom_id = ?").toString();

    private static final String UPDATE_MESSAGE_SQL = new StringBuilder()
            .append("UPDATE messages ")
            .append("SET author_id = ?, chatroom_id = ?, message = ?, message_date = ? ")
            .append("WHERE message_id = ?").toString();


    private static final String SAVE_MESSAGE_SQL = new StringBuilder()
            .append("INSERT INTO messages (author_id, chatroom_id, message, message_date) ")
            .append("VALUES (?, ?, ?, ?)").toString();


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
            throw new RepositoryException(e);
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

    public void updateUser(User user) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_SQL)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setLong(3, user.getId());
            if (preparedStatement.executeUpdate() == 0) {
                throw new NotSavedSubEntityException("Can't update user. User ID " + user.getId() + " doesn't exist.");
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    public void updateChatroom(Chatroom chatroom) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CHATROOM_SQL)) {
            preparedStatement.setString(1, chatroom.getName());
            preparedStatement.setLong(2, chatroom.getOwner().getId());
            preparedStatement.setLong(3, chatroom.getId());
            if (preparedStatement.executeUpdate() == 0) {
                throw new NotSavedSubEntityException("Can't update chatroom. Chatroom ID " + chatroom.getId()
                        + " doesn't exist.");
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    public void saveMessage(Message message) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_MESSAGE_SQL,
                     Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, message.getAuthor().getId());
            preparedStatement.setLong(2, message.getChatroom().getId());
            preparedStatement.setString(3, message.getMessage());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(message.getLocalDateTime()));
            preparedStatement.setLong(5, message.getId());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                message.setId(generatedKeys.getLong("message_id"));
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void save(Message message) {
        updateUser(message.getAuthor());
        updateChatroom(message.getChatroom());
        saveMessage(message);
    }

    @Override
    public void update(Message message) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MESSAGE_SQL)) {
            preparedStatement.setLong(1, message.getAuthor().getId());
            preparedStatement.setLong(2, message.getChatroom().getId());
            preparedStatement.setString(3, message.getMessage());
            if (message.getLocalDateTime() == null) {
                preparedStatement.setNull(4, Types.TIMESTAMP);
            } else {
                preparedStatement.setTimestamp(4, Timestamp.valueOf(message.getLocalDateTime()));
            }
            preparedStatement.setLong(5, message.getId());

            if (preparedStatement.executeUpdate() == 0) {
                throw new NotSavedSubEntityException("Can't update message. Message ID " + message.getId()
                        + " doesn't exist.");
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }
}
