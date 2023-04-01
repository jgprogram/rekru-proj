package com.vattenfall.bookstore.infrastructure.endpoint

import com.vattenfall.bookstore.application.BookDto
import com.vattenfall.bookstore.domain.Author
import com.vattenfall.bookstore.domain.AuthorRepository
import com.vattenfall.bookstore.domain.Book
import com.vattenfall.bookstore.domain.BookstoreRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import spock.lang.Specification
import spock.lang.Subject

@SpringBootTest
class BookstoreRestControllerIntegrationTest extends Specification {

    @Subject
    @Autowired
    BookstoreRestController bookstoreRestController

    @Autowired
    BookstoreRepository bookstoreRepository

    @Autowired
    AuthorRepository authorRepository

    def "should get books from store"() {
        given:
        def book1 = new Book("0-4327-9079-9", "Book One", 1)
        def book2 = new Book("0-5384-4445-2", "Book Two", 12)
        bookstoreRepository.save(book1)
        bookstoreRepository.save(book2)

        when:
        def result = bookstoreRestController.getBooks()

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

    def "should add book to the store"() {
        given:
        def book = new BookDto("0-6912-1477-8", "Test book", 1)
        def author = new Author(1, "John", "Doe")
        authorRepository.save(author)

        when:
        def response = bookstoreRestController.postBook(book)

        then:
        response.statusCode == HttpStatus.OK

        and:
        def allBooks = bookstoreRepository.findAll()
        allBooks.find({
            it.isbn() == book.isbn()
            it.title() == book.title()
            it.authorId() == book.authorId()
        }) != null
    }

    def "should return bad request in case of #cause"() {
        when:
        def response = bookstoreRestController.postBook(book)

        then:
        response.statusCode == HttpStatus.BAD_REQUEST

        where:
        cause                 | book
        "not-existent author" | new BookDto("0-9952-1600-2", "New book", 2)
        "invalid isbn"        | new BookDto("978-2-3765-8750-7", "New book", 1)
        "invalid title"       | new BookDto("0-9952-1600-2", "", 1)
        "duplicated book"     | new BookDto("0-6912-1477-8", "New book", 1)
    }
}
