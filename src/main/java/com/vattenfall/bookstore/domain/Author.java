package com.vattenfall.bookstore.domain;

public class Author {

    private final int id;
    private final String name;
    private final String surname;

    public Author(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public int id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String surname() {
        return surname;
    }
}
