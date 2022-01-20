package com.vattenfall.bookstore.application;

public class BookDto {

    private final String isbn;
    private final String title;
    private final int authorId;

    public BookDto(String isbn, String title, int authorId) {
        this.isbn = isbn;
        this.title = title;
        this.authorId = authorId;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public int getAuthorId() {
        return authorId;
    }
}
