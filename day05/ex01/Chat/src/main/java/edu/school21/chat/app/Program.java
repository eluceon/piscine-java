package edu.school21.chat.app;

import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.DataSource;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.util.Optional;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MessagesRepositoryJdbcImpl messagesRepositoryJdbc = new MessagesRepositoryJdbcImpl(DataSource.getInstance());

        while(true) {
            System.out.print("Enter a message ID\n-> ");
            if (scanner.hasNextLong()) {
                Optional<Message> maybeMessage = messagesRepositoryJdbc.findById(scanner.nextLong());
                if (maybeMessage.isPresent()) {
                    System.out.println(maybeMessage.get());
                } else {
                    System.out.println("ID not found");
                }
            } else if (scanner.next() .equals("exit")) {
                break;
            }
        }
    }
}
