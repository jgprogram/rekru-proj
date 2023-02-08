package com.vattenfall.bookstore.application;

public class InvalidBookDataException extends Exception {

    public InvalidBookDataException(String message) {
        super(message);
    }

    public static InvalidBookDataException forInvalidIsbn() {
        return new InvalidBookDataException("Isbn must have 13 signs");
    }

    public static InvalidBookDataException forInvalidTitle() {
        return new InvalidBookDataException("Title must have at least one sign");
    }
}
