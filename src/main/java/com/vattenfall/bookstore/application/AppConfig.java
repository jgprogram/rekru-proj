package com.vattenfall.bookstore.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vattenfall.bookstore.domain.AuthorRepository;
import com.vattenfall.bookstore.domain.BookstoreRepository;

@Configuration
class AppConfig {

    @Bean
    BookstoreApplicationService bookstoreApplicationService(BookstoreRepository bookstoreRepository, AuthorRepository authorRepository) {
        return new BookstoreApplicationService(bookstoreRepository, authorRepository);
    }
}
