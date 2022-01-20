package com.vattenfall.bookstore.domain;

public class Book {

    private final String isbn;
    private final String title;
    private final int authorId;

    public Book(String isbn, String title, int authorId) {
        this.isbn = isbn;
        this.title = title;
        this.authorId = authorId;
    }

    public String isbn() {
        return isbn;
    }

    public String title() {
        return title;
    }

    public int authorId() {
        return authorId;
    }
}
