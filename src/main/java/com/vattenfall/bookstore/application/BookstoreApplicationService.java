package com.vattenfall.bookstore.application;

import java.util.List;

import com.vattenfall.bookstore.domain.Author;
import com.vattenfall.bookstore.domain.AuthorRepository;
import com.vattenfall.bookstore.domain.Book;
import com.vattenfall.bookstore.domain.BookstoreRepository;

import jakarta.transaction.Transactional;

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
                                  .toList();
    }

    @Transactional
    public void addBook(BookDto bookDto) throws AuthorDoesNotExistsException, InvalidBookDataException {
        Author author = authorRepository.findById(bookDto.authorId())
                                        .orElseThrow(AuthorDoesNotExistsException::new);
        assertBookIsCorrect(bookDto);
        Book book = new Book(bookDto.isbn(), bookDto.title(), author.id());
        bookstoreRepository.save(book);
    }

    private void assertBookIsCorrect(BookDto bookDto) throws InvalidBookDataException {
        if(bookDto.isbn() == null || bookDto.isbn().length() != 13) {
            throw InvalidBookDataException.forInvalidIsbn();
        }

        if(bookDto.title() == null || bookDto.title().length() < 1) {
            throw InvalidBookDataException.forInvalidTitle();
        }
    }
}
