package edu.school21.chat.exception;

public class RepositoryException extends RuntimeException {
    public RepositoryException(Throwable throwable) {
        super(throwable);
    }
}