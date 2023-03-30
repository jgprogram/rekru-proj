package com.vattenfall.bookstore.application

import com.vattenfall.bookstore.domain.Author
import com.vattenfall.bookstore.domain.AuthorRepository
import com.vattenfall.bookstore.domain.Book
import com.vattenfall.bookstore.domain.BookstoreRepository
import spock.lang.Specification
import spock.lang.Subject

class BookstoreApplicationServiceTest extends Specification {

    BookstoreRepository bookstoreRepositoryMock = Mock()
    AuthorRepository authorRepositoryMock = Mock()

    @Subject
    BookstoreApplicationService bookstoreApplicationService = new BookstoreApplicationService(bookstoreRepositoryMock, authorRepositoryMock)

    def "should get books from store"() {
        given:
        def book1 = new Book("0-4327-9079-9", "Book One", 1)
        def book2 = new Book("0-5384-4445-2", "Book Two", 12)
        bookstoreRepositoryMock.findAll() >> [book1, book2]

        when:
        def result = bookstoreApplicationService.getBooks()

        then:
        result != null
        result.size() == 2
        with(result.get(0)) { book ->
            book.isbn() == book1.isbn()
            book.title() == book1.title()
            book.authorId() == book1.authorId()
        }
        with(result.get(1)) { book ->
            book.isbn() == book2.isbn()
            book.title() == book2.title()
            book.authorId() == book2.authorId()
        }
    }

    def "should return empty result if store has no books"() {
        given:
        bookstoreRepositoryMock.findAll() >> []

        when:
        def result = bookstoreApplicationService.getBooks()

        then:
        result != null
        result.size() == 0
    }

    def "should add book to store"() {
        given:
        def book = new BookDto("0-4327-9079-9", "Test book", 1)
        authorRepositoryMock.findById(1) >> Optional.of(new Author(1, "John", "Doe"))

        when:
        bookstoreApplicationService.addBook(book)

        then:
        1 * bookstoreRepositoryMock.save(new Book(book.isbn(), book.title(), book.authorId()))

        and:
        noExceptionThrown()
    }

    def "should validate if author exists in store"() {
        given:
        def book = new BookDto("0-4327-9079-9", "Test book", 1)
        authorRepositoryMock.findById(1) >> Optional.empty()

        when:
        bookstoreApplicationService.addBook(book)

        then:
        0 * bookstoreRepositoryMock.save(_)

        and:
        thrown(AuthorDoesNotExistsException)
    }

    def "should validate if isbn is correct"() {
        given:
        def book = new BookDto("978-2-3765-8750-7", "Test book", 1)
        authorRepositoryMock.findById(1) >> Optional.of(new Author(1, "John", "Doe"))

        when:
        bookstoreApplicationService.addBook(book)

        then:
        0 * bookstoreRepositoryMock.save(_)

        and:
        def exception = thrown(InvalidBookDataException)
        exception.message == InvalidBookDataException.forInvalidIsbn().message
    }

    def "should validate if title is not empty"() {
        given:
        def book = new BookDto("0-4327-9079-9", "", 1)
        authorRepositoryMock.findById(1) >> Optional.of(new Author(1, "John", "Doe"))

        when:
        bookstoreApplicationService.addBook(book)

        then:
        0 * bookstoreRepositoryMock.save(_)

        and:
        def exception = thrown(InvalidBookDataException)
        exception.message == InvalidBookDataException.forInvalidTitle().message
    }

    def "should validate if book is not a duplicate"() {
        given:
        def book = new BookDto("0-4327-9079-9", "", 1)
        authorRepositoryMock.findById(1) >> Optional.of(new Author(1, "John", "Doe"))
        bookstoreRepositoryMock.existsByIsbn("0-4327-9079-9") >> true

        when:
        bookstoreApplicationService.addBook(book)

        then:
        0 * bookstoreRepositoryMock.save(_)

        and:
        thrown(InvalidBookDataException)
    }
}
