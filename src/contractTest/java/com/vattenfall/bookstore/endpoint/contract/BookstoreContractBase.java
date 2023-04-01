package com.vattenfall.bookstore.endpoint.contract;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vattenfall.bookstore.application.BookDto;
import com.vattenfall.bookstore.application.BookstoreApplicationService;
import com.vattenfall.bookstore.infrastructure.endpoint.BookstoreRestController;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.quality.Strictness.LENIENT;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = LENIENT)
public abstract class BookstoreContractBase {

    @Mock
    BookstoreApplicationService bookstoreApplicationService;

    @InjectMocks
    BookstoreRestController bookstoreRestController;

    @BeforeEach
    void setup() {
        when(bookstoreApplicationService.getBooks()).thenReturn(List.of(
                new BookDto("0-4327-9079-9", "Book One", 1),
                new BookDto("0-5384-4445-2", "Book Two", 12)
        ));

        var converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(new ObjectMapper());
        StandaloneMockMvcBuilder standaloneMockMvcBuilder = MockMvcBuilders.standaloneSetup(bookstoreRestController)
                .setMessageConverters(converter);
        RestAssuredMockMvc.standaloneSetup(standaloneMockMvcBuilder);
    }
}
