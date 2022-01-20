package com.vattenfall.bookstore.application;

import java.util.List;

import com.vattenfall.bookstore.domain.Author;
import com.vattenfall.bookstore.domain.AuthorRepository;
import com.vattenfall.bookstore.domain.Book;
import com.vattenfall.bookstore.domain.BookstoreRepository;

import static java.util.stream.Collectors.toUnmodifiableList;

public class BookstoreApplicationService {

    private final BookstoreRepository bookstoreRepository;
    private final AuthorRepository authorRepository;

    BookstoreApplicationService(BookstoreRepository bookstoreRepository, AuthorRepository authorRepository) {
        this.bookstoreRepository = bookstoreRepository;
        this.authorRepository = authorRepository;
    }

    public List<BookDto> getBooks() {
        return bookstoreRepository.findAll().stream()
                                  .map(book -> new BookDto(book.isbn(), book.title(), book.authorId()))
                                  .collect(toUnmodifiableList());
    }

    public void addBook(BookDto bookDto) throws AuthorDoesNotExistsException {
        Author author = authorRepository.findById(bookDto.getAuthorId())
                                        .orElseThrow(AuthorDoesNotExistsException::new);
        Book book = new Book(bookDto.getIsbn(), bookDto.getTitle(), author.id());
        bookstoreRepository.save(book);
    }
}
