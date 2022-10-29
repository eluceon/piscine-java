package edu.school21.chat.models;

import java.util.Date;
import java.util.Objects;

public class Message {
    private Long id;
    private Long authorId;
    private Long roomId;
    private String message;
    private Date datetime;

    public Message(Long authorId, Long roomId, String message, Date datetime) {
        this.authorId = authorId;
        this.roomId = roomId;
        this.message = message;
        this.datetime = datetime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Message message1 = (Message) o;
        return id.equals(message1.id) && authorId.equals(message1.authorId) && roomId.equals(message1.roomId)
                && message.equals(message1.message) && datetime.equals(message1.datetime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authorId, roomId, message, datetime);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", authorId=" + authorId +
                ", roomId=" + roomId +
                ", message='" + message + '\'' +
                ", datetime=" + datetime +
                '}';
    }
}
