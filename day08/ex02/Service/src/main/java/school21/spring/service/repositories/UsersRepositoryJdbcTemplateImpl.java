package school21.spring.service.repositories;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String FIND_ALL = "SELECT * FROM users";

    private static final String FIND_USER_BY_ID_SQL = FIND_ALL + " WHERE user_id = :id";
    private static final String FIND_USER_BY_EMAIL_SQL = FIND_ALL + " WHERE user_email = :email";

    private static final String UPDATE_USER_SQL = new StringBuilder()
            .append("UPDATE users ")
            .append("SET user_email = :email, ")
            .append("user_password = :password ")
            .append("WHERE user_id = :id").toString();

    private static final String SAVE_USER_SQL = new StringBuilder()
            .append("INSERT INTO users (user_email, user_password) ")
            .append("VALUES (:email, :password)").toString();

    private static final String DELETE_USER_SQL = "DELETE FROM users WHERE user_id = :id";

    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public User findById(Long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        try {
            User user = namedParameterJdbcTemplate.queryForObject(FIND_USER_BY_ID_SQL,
                    namedParameters, new UserMapper());
            return user;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<User> findAll() {
        return namedParameterJdbcTemplate.query(FIND_ALL, new UserMapper());
    }

    @Override
    public void save(User user) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("email", user.getEmail())
                .addValue("password", user.getPassword());
        namedParameterJdbcTemplate.update(SAVE_USER_SQL, namedParameters);
    }

    @Override
    public void update(User user) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("email", user.getEmail())
                .addValue("password", user.getPassword()).addValue("id", user.getId());
        namedParameterJdbcTemplate.update(UPDATE_USER_SQL, namedParameters);
    }

    @Override
    public void delete(Long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        namedParameterJdbcTemplate.update(DELETE_USER_SQL, namedParameters);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("email", email);
        try {
            User user = namedParameterJdbcTemplate.queryForObject(FIND_USER_BY_EMAIL_SQL,
                    namedParameters, new UserMapper());
            return (Optional.ofNullable(user));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private static final class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new User(rs.getLong("user_id"),
                    rs.getString("user_email"),
                    rs.getString("user_password"));
        }
    }
}
