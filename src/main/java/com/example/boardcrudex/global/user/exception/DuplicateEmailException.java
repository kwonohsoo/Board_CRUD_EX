package com.example.boardcrudex.global.user.exception;

public class DuplicateEmailException  extends RuntimeException {
    public DuplicateEmailException(String message) {
        super(message);
    }
}
