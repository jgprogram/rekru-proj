package com.vattenfall.bookstore.domain;

public record Book(
        String isbn,
        String title,
        int authorId
) {

}
