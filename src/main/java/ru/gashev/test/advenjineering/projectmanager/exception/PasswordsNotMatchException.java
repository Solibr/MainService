package ru.gashev.test.advenjineering.projectmanager.exception;

public class PasswordsNotMatchException extends RuntimeException {
    public PasswordsNotMatchException(String message) {
        super(message);
    }
}
