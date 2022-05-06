package school21.spring.service.repositories;

import school21.spring.service.models.User;


import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository{
    private final DataSource dataSource;
    private static final String FIND_ALL = "SELECT * FROM users";

    private static final String FIND_USER_BY_ID_SQL = FIND_ALL + " WHERE user_id = ?";
    private static final String FIND_USER_BY_EMAIL_SQL = FIND_ALL + " WHERE user_email = ?";

    private static final String UPDATE_USER_SQL = new StringBuilder()
            .append("UPDATE users ")
            .append("SET user_email = ? ")
            .append("WHERE user_id = ?").toString();

    private static final String SAVE_USER_SQL = new StringBuilder()
            .append("INSERT INTO users (user_email) ")
            .append("VALUES (?)").toString();

    private static final String DELETE_USER_SQL = "DELETE FROM users WHERE user_id = ?";


    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public User findById(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = new User(resultSet.getLong("user_id"), resultSet.getString("user_email"));
            }

            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List findAll() {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(new User(resultSet.getLong("user_id"),
                        resultSet.getString("user_email")));
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(User user) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_USER_SQL,
                     Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getEmail());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User user) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_SQL)) {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setLong(2, user.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_SQL)) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional findByEmail(String email) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_EMAIL_SQL)) {
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = new User(resultSet.getLong("user_id"), resultSet.getString("user_email"));
            }

            return Optional.ofNullable(user);
        } catch (SQLException e) {
                throw new RuntimeException(e);
        }
    }
}
