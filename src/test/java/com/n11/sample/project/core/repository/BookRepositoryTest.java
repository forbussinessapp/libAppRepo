package com.n11.sample.project.core.repository;

import com.n11.sample.project.core.model.Author;
import com.n11.sample.project.core.model.Book;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by mkurucay on 10.02.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath*:spring/context.app-data-test.xml"})
public class BookRepositoryTest {

    @Autowired
    BookRepository repository;

    static Book book;

    @Before
    public void init() {
        final Author author = new Author();
        author.setFirstName("Joshua Kerievsky");

        if(book != null){
            return;
        }
        book = new Book(author, "Refactoring to Patterns");
        book = repository.save(book);
    }


    @Test
    public void testFindByTitle() {
        final Book book = repository.findByTitle("Refactoring to Patterns");
        Assert.assertNotNull(book);
    }

    @Test
    public void testNotFindByTitle() {
        final Book book = repository.findByTitle("Test Driven Development");
        Assert.assertNull(book);
    }
}
