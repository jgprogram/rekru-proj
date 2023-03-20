package com.vattenfall.bookstore.infrastructure.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

interface BookDao extends JpaRepository<BookEntity, Long> {

    boolean existsByIsbn(String isbn);

    List<BookEntity> findAllByOrderByIsbn();
}