package com.n11.sample.project.core.service;

import com.n11.sample.project.core.model.Book;

import java.util.List;

/**
 * Created by mkurucay on 10.02.2017.
 */
public interface BookService {
    String MESSAGE_CREATE = "Book successfully created";
    String MESSAGE_FETCH = "Books successfully fetched";
    String MESSAGE_UPDATE = "Book successfully updated";
    String MESSAGE_DELETE = "Book successfully deleted";

    /**
     * @param book the Book containing the data to be used for creating the new entity
     * @return the created Book with a generated ID
     * @throws com.n11.sample.project.core.service.exception.BookAlreadyExistsException if the book to add to already be created
     */
    Book createBook(final Book book);

    /**
     * @param id the id of the book to add this Book to
     * @param book the Book containing the data to be used for creating the new entity
     * @return the created Book
     * @throws com.n11.sample.project.core.service.exception.BookNotFoundException if the book to add to cannot be found
     */
    Book updateBook(String id, final Book book);

    /**
     * @param id the id of the book to add this Book to
     * @param book the Book containing the data to be used for creating the new entity
     * @return the created Book
     * @throws com.n11.sample.project.core.service.exception.BookNotFoundException if the book to delete to cannot be found
     */
    Book deleteBook(String id, final Book book);

    /**
     * @return Book list
     * @throws com.n11.sample.project.core.service.exception.NoBookFoundException if no book found in the database
     */
    List<Book> getAllBooks();

    Book findBookById(final String id);
}
