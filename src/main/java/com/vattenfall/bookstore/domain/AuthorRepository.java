package com.vattenfall.bookstore.domain;

import java.util.Optional;

public interface AuthorRepository {

    Optional<Author> findById(int authorId);

    void save(Author author);
}
