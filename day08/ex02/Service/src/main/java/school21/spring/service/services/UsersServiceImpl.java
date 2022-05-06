package school21.spring.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;

import java.util.Optional;
import java.util.Random;

@Component("usersServiceBean")
public class UsersServiceImpl implements UsersService {
    @Autowired
    @Qualifier("usersRepositoryJdbcTemplate")
    private UsersRepository repository;
    @Override
    public String signUp(String email) {
        String password = null;
        Optional<User> optionalUser = repository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            password = user.getPassword();
            if (password == null) {
                password = generatePassword();
                user.setPassword(password);
                repository.update(user);
            }
        } else {
            password = generatePassword();
            repository.save(new User(email, password));
        }
        return password;
    }

    private String generatePassword() {
        StringBuilder password = new StringBuilder();
        Random random = new Random();
        final int PASSWORD_LEN = 10 + random.nextInt(10);

        for (int i = 0; i < PASSWORD_LEN; ++i) {
            password.append((char)(random.nextInt(95) + 32));
        }
        return password.toString();
    }
}
