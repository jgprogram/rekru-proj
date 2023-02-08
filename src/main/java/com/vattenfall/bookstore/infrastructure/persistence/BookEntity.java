package com.vattenfall.bookstore.infrastructure.persistence;

import org.hibernate.annotations.NaturalId;

import com.vattenfall.bookstore.domain.Book;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
class BookEntity {

    protected BookEntity() {
    }

    BookEntity(String isbn, String title, int authorId) {
        this.isbn = isbn;
        this.title = title;
        this.authorId = authorId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    @Column(nullable = false)
    private String isbn;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private int authorId;

    Book toDomainObject() {
        return new Book(isbn, title, authorId);
    }

    String isbn() {
        return isbn;
    }

    String title() {
        return title;
    }

    int authorId() {
        return authorId;
    }
}