package com.vattenfall.bookstore.infrastructure.persistence;

import com.vattenfall.bookstore.domain.Author;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
class AuthorEntity {

    protected AuthorEntity() {
    }

    AuthorEntity(int authorId, String name, String surname) {
        this.authorId = authorId;
        this.name = name;
        this.surname = surname;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int authorId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    public Long id() {
        return id;
    }

    Author toDomainObject() {
        return new Author(authorId, name, surname);
    }

    int authorId() {
        return authorId;
    }

    String name() {
        return name;
    }

    String surname() {
        return surname;
    }
}