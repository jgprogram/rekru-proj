package com.vattenfall.bookstore.infrastructure.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

interface BookDao extends JpaRepository<BookEntity, Long> {

    Optional<BookEntity> findByIsbn(String isbn);

    List<BookEntity> findAllByOrderByIsbn();
}