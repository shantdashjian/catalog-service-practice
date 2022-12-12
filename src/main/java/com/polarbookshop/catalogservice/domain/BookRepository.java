package com.polarbookshop.catalogservice.domain;

import com.polarbookshop.catalogservice.domain.Book;

import java.util.Optional;

public interface BookRepository {
    public Iterable<Book> findAll();

    public Optional<Book> findByIsbn(String isbn);

    public Book save(Book book);

    public boolean existsByIsbn(String isbn);

    public void deleteByIsbn(String isbn);
}
