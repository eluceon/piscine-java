package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class User {
    private Long id;
    private String login;
    private String password;
    private List<Chatroom> activeRooms;
    private List<Chatroom>  createdRooms;

    public User(Long id, String login, String password, List<Chatroom> activeRooms, List<Chatroom> createdRooms) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.activeRooms = activeRooms;
        this.createdRooms = createdRooms;
    }

    public User(Long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public List<Chatroom> getActiveRooms() {
        return activeRooms;
    }

    public List<Chatroom> getCreatedRooms() {
        return createdRooms;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setActiveRooms(List<Chatroom> activeRooms) {
        this.activeRooms = activeRooms;
    }

    public void setCreatedRooms(List<Chatroom> createdRooms) {
        this.createdRooms = createdRooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return id == user.id && login.equals(user.login) && password.equals(user.password)
                && Objects.equals(activeRooms, user.activeRooms) && Objects.equals(createdRooms, user.createdRooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, activeRooms, createdRooms);
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", created rooms=" + createdRooms +
                ", rooms=" + activeRooms +
                '}';
    }
}

