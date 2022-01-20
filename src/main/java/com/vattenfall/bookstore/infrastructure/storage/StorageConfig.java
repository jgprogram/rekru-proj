package com.vattenfall.bookstore.infrastructure.storage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vattenfall.bookstore.domain.AuthorRepository;
import com.vattenfall.bookstore.domain.BookstoreRepository;

@Configuration
class StorageConfig {

    @Bean
    AuthorRepository authorRepository() {
        return new InMemoryAuthorRepository();
    }

    @Bean
    BookstoreRepository bookstoreRepository() {
        return new InMemoryBookstoreRepository();
    }
}
