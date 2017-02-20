package com.n11.sample.project.core.service;

import com.n11.sample.project.core.model.Author;
import com.n11.sample.project.core.model.Book;
import com.n11.sample.project.core.repository.BookRepository;
import com.n11.sample.project.core.service.impl.BookServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Created by mkurucay on 10.02.2017.
 */
@RunWith(JUnit4.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    private BookService bookService;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        bookService = new BookServiceImpl(bookRepository);
    }

    @Test
    public void testFindById(){
        final Author author = new Author();
        author.setFirstName("Kent");
        author.setLastName("Beck");
        final Book mockBook = new Book(author, "Test Driven Development");

        Mockito.when(bookRepository.findByTitle(mockBook.getTitle())).thenReturn(mockBook);

        final Book book = bookService.findBookById(mockBook.getId());
        Assert.assertNull(book);
    }

    @Test
    public void testNotFindBy(){
        final String id = "1";
        final String title = "Kurk Mantolu Madonna";

        Mockito.when(bookRepository.findByTitle(title)).thenReturn(null);

        final Book book = bookService.findBookById(id);
        Assert.assertNull(book);
    }
}
