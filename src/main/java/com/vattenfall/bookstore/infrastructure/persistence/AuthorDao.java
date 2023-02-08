package com.vattenfall.bookstore.infrastructure.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

interface AuthorDao extends JpaRepository<AuthorEntity, Long> {

    Optional<AuthorEntity> findByAuthorId(int authorId);
}