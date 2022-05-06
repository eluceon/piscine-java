package school21.spring.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import school21.spring.service.repositories.UsersRepositoryJdbcImpl;
import school21.spring.service.repositories.UsersRepositoryJdbcTemplateImpl;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Configuration
@ComponentScan("school21.spring.service.services")
public class TestApplicationConﬁg {
    private EmbeddedDatabase dataSource;
    private final String SQL_DROP_TABLE = "DROP TABLE userdb IF EXISTS; ";

    private final String SQL_CREATE_TABLE =
            "CREATE TABLE users (user_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY," +
                    " user_email VARCHAR(255), user_password VARCHAR(255))";

    @Bean
    public DataSource hsqlDataSource() {
        try {
            dataSource = new EmbeddedDatabaseBuilder().build();
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_DROP_TABLE);
            statement.execute();
            statement = connection.prepareStatement(SQL_CREATE_TABLE);
            statement.execute();
            return dataSource;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public UsersRepositoryJdbcTemplateImpl usersRepositoryJdbcTemplate() {
        return new UsersRepositoryJdbcTemplateImpl(hsqlDataSource());
    }

    @Bean
    public UsersRepositoryJdbcImpl usersRepositoryJdbc() {
        return new UsersRepositoryJdbcImpl(hsqlDataSource());
    }

}
