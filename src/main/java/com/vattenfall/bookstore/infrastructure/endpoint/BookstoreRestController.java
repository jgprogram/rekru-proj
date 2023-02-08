package com.vattenfall.bookstore.infrastructure.endpoint;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vattenfall.bookstore.application.AuthorDoesNotExistsException;
import com.vattenfall.bookstore.application.BookDto;
import com.vattenfall.bookstore.application.BookstoreApplicationService;
import com.vattenfall.bookstore.application.InvalidBookDataException;

@RestController
@RequestMapping("/books")
public class BookstoreRestController {

    private final BookstoreApplicationService bookstoreApplicationService;

    BookstoreRestController(BookstoreApplicationService bookstoreApplicationService) {
        this.bookstoreApplicationService = bookstoreApplicationService;
    }

    @GetMapping
    public List<BookDto> getBooks() {
        return bookstoreApplicationService.getBooks();
    }

    @PutMapping
    public ResponseEntity<Void> putBook(@RequestBody BookDto bookDto) {
        try {
            bookstoreApplicationService.addBook(bookDto);
            return ResponseEntity.ok().build();
        } catch (AuthorDoesNotExistsException | InvalidBookDataException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
