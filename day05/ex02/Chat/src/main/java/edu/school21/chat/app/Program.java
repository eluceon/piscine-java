package edu.school21.chat.app;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.DataSource;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;


public class Program {
    public static void main(String[] args) {

        User creator = new User(5L, "user", "ser", new ArrayList(), new ArrayList());
        User author = creator;
        Chatroom room = new Chatroom(2L, "room", creator, new ArrayList());
        Message message = new Message(null, author, room, "Hello!", LocalDateTime.now());
        MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(DataSource.getInstance());
        messagesRepository.save(message);
        System.out.println(message.getId());
    }
}
