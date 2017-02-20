package com.n11.sample.project.core.service.impl;

import com.n11.sample.project.core.model.Book;
import com.n11.sample.project.core.repository.BookRepository;
import com.n11.sample.project.core.service.BookService;
import com.n11.sample.project.core.service.exception.BookAlreadyExistsException;
import com.n11.sample.project.core.service.exception.BookNotFoundException;
import com.n11.sample.project.core.service.exception.NoBookFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mkurucay on 10.02.2017.
 */
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book createBook(Book book) {
        Book dbBook = bookRepository.findByTitle(book.getTitle());
        if(dbBook != null)
        {
            throw new BookAlreadyExistsException();
        }
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(String id, Book book) {
        final Book dbBook = bookRepository.findOne(id);
        if(dbBook == null)
        {
            throw new BookNotFoundException();
        }
        dbBook.setTitle(book.getTitle());
        dbBook.setAuthor(book.getAuthor());
        return bookRepository.save(dbBook);
    }

    @Override
    public Book deleteBook(String id, Book book) {
        final Book dbBook = bookRepository.findOne(id);
        if(dbBook == null)
        {
            throw new BookNotFoundException();
        }
        bookRepository.delete(dbBook.getId());
        return dbBook;
    }

    @Override
    public Book findBookById(String id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> getAllBooks() {
        final List<Book> books = bookRepository.findAll();
        if(books == null){
            throw new NoBookFoundException();
        }
        return books;
    }
}
