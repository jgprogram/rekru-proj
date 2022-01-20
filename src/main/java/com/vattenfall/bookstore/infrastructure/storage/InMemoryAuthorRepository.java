package com.vattenfall.bookstore.infrastructure.storage;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import com.vattenfall.bookstore.domain.Author;
import com.vattenfall.bookstore.domain.AuthorRepository;

import static java.util.Optional.ofNullable;

class InMemoryAuthorRepository implements AuthorRepository {

    private final Map<Integer, Author> authors = new ConcurrentHashMap<>();

    InMemoryAuthorRepository() {
    }

    @Override
    public Optional<Author> findById(int authorId) {
        return ofNullable(authors.get(authorId));
    }

    @Override
    public void save(Author author) {
        authors.putIfAbsent(author.id(), author);
    }
}
