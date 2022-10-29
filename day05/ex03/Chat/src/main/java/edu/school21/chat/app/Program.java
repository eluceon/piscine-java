package edu.school21.chat.app;

import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.DataSource;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.util.Optional;

public class Program {
    public static void main(String args[]) {
        MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(DataSource.getInstance());
        Optional<Message> messageOptional = messagesRepository.findById(02L);
        if (messageOptional.isPresent()) {
            Message message = messageOptional.get();
            message.setMessage("Bye");
            message.setLocalDateTime(null);
            messagesRepository.update(message);
        }
    }
}
