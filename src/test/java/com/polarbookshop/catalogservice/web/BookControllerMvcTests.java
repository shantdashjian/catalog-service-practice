package com.polarbookshop.catalogservice.web;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.domain.BookNotFoundException;
import com.polarbookshop.catalogservice.domain.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;

@WebMvcTest(BookController.class)
class BookControllerMvcTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BookService bookService;

    @Test
    void whenGetBookThenShouldReturnBook() throws Exception {
        Book book =
                new Book("1234567891", "Cloud Native", "Vitale", 19.99);
        given(bookService.viewBookDetails(book.isbn()))
                .willReturn(book);
        mockMvc
                .perform(get("/books/" + book.isbn()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isbn", is(book.isbn())))
                .andExpect(jsonPath("$.title", is(book.title())));
    }

    @Test
    void whenGetBookNotExisitngThenShouldReturn404() throws Exception {
        Book book =
                new Book("1234567891", "Cloud Native", "Vitale", 19.99);
        given(bookService.viewBookDetails(book.isbn()))
                .willThrow(BookNotFoundException.class);
        mockMvc
                .perform(get("/books/" + book.isbn()))
                .andExpect(status().isNotFound());
    }

}
