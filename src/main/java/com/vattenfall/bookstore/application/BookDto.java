package com.vattenfall.bookstore.application;

public record BookDto(
        String isbn,
        String title,
        int authorId
) {

}
