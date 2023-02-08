package com.vattenfall.bookstore.infrastructure.persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vattenfall.bookstore.domain.AuthorRepository;
import com.vattenfall.bookstore.domain.BookstoreRepository;

@Configuration
class PersistenceConfig {

    @Bean
    AuthorRepository authorRepository(AuthorDao authorDao) {
        return new AuthorOrmRepository(authorDao);
    }

    @Bean
    BookstoreRepository bookstoreRepository(BookDao bookDao) {
        return new BookstoreOrmRepository(bookDao);
    }
}