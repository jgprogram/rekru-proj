package com.vattenfall.bookstore.domain;

import java.util.List;

public interface BookstoreRepository {

    void save(Book book);

    List<Book> findAll();

    boolean existsByIsbn(String isbn);
}
