package school21.spring.service.models;

import java.util.Objects;

public class User {
    private Long id;
    private String email;
    private String password;

    public User(Long id, String email) {
        setId(id);
        setEmail(email);
    }

    public User(String email) {
        setEmail(email);
    }

    public User(String email, String password) {
        setEmail(email);
        setPassword(password);
    }

    public User(Long id, String email, String password) {
        setId(id);
        setEmail(email);
        setPassword(password);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) && email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
