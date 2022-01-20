package com.vattenfall.bookstore.infrastructure.storage;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.vattenfall.bookstore.domain.Book;
import com.vattenfall.bookstore.domain.BookstoreRepository;

import static java.util.Collections.unmodifiableList;

class InMemoryBookstoreRepository implements BookstoreRepository {

    private final List<Book> books = new CopyOnWriteArrayList<>();

    InMemoryBookstoreRepository() {
    }

    @Override
    public void save(Book book) {
        books.add(book);
    }

    @Override
    public List<Book> findAll() {
        return unmodifiableList(books);
    }
}
