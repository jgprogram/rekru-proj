package com.vattenfall.bookstore.infrastructure.persistence;

import java.util.List;

import com.vattenfall.bookstore.domain.Book;
import com.vattenfall.bookstore.domain.BookstoreRepository;

class BookstoreOrmRepository implements BookstoreRepository {

    private final BookDao bookDao;

    BookstoreOrmRepository(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public void save(Book book) {
        if(bookDao.findByIsbn(book.isbn()).isPresent()) {
            return;
        }

        bookDao.save(new BookEntity(
                book.isbn(),
                book.title(),
                book.authorId()
        ));
    }

    @Override
    public List<Book> findAll() {
        return bookDao.findAllByOrderByIsbn().stream()
                      .map(BookEntity::toDomainObject)
                      .toList();
    }
}