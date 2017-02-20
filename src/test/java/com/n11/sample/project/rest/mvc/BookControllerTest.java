package com.n11.sample.project.rest.mvc;

import com.n11.sample.project.core.model.Author;
import com.n11.sample.project.core.model.Book;
import com.n11.sample.project.core.service.BookService;
import com.n11.sample.project.rest.mvc.controller.BookController;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mkurucay on 10.02.2017.
 */
@RunWith(JUnit4.class)
public class BookControllerTest {
    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookControl;

    private MockMvc mockMvc;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookControl).build();
    }

    @Test
    public void testFindAll() throws Exception{
        final List<Book> books = new ArrayList<Book>();

        final Author author1 = new Author();
        author1.setFirstName("Joshua");
        author1.setLastName("Kerievsky");
        final Book book1 = new Book(author1, "Refactoring to Patterns");
        books.add(book1);

        final Author author2 = new Author();
        author2.setFirstName("Kent");
        author2.setLastName("Beck");
        final Book book2 = new Book(author2, "Test Driven Development");
        books.add(book2);

        Mockito.when(bookService.getAllBooks()).thenReturn(books);

        mockMvc.perform(MockMvcRequestBuilders.get("/rest/books"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.object").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.object", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.object[*].title",
                        Matchers.hasItems(
                                Matchers.endsWith("Refactoring to Patterns"),
                                Matchers.endsWith("Test Driven Development"))))
                .andExpect(MockMvcResultMatchers.jsonPath("$.object[*].author.firstName",
                        Matchers.hasItems(
                                Matchers.endsWith("Joshua"),
                                Matchers.endsWith("Kent"))))
                .andExpect(MockMvcResultMatchers.jsonPath("$.object[*].author.lastName",
                        Matchers.hasItems(
                                Matchers.endsWith("Kerievsky"),
                                Matchers.endsWith("Beck"))))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    public void  testCreateBook() throws Exception {
        final Author author = new Author();
        author.setFirstName("Joshua");
        author.setLastName("Kerievsky");
        final Book book = new Book(author, "Refactoring to Patterns");

        Mockito.when(bookService.createBook(Mockito.any(Book.class))).thenReturn(book);

        mockMvc.perform(MockMvcRequestBuilders.post("/rest/books")
                .content("{\"id\": \"\",\"title\": \"Refactoring to Patterns\",\"author\": {\"id\": \"\",\"firstName\": \"Joshua\",\"lastName\": \"Kerievsky\"}}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.object.title", Matchers.is("Refactoring to Patterns")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.object.author.firstName", Matchers.is("Joshua")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.object.author.lastName", Matchers.is("Kerievsky")))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void  testUpdateBook() throws Exception {
        final Author author = new Author();
        author.setFirstName("Robert C.");
        author.setLastName("Martin");
        final Book book = new Book(author, "Clean Code");
        book.setId("1");

        Mockito.when(bookService.updateBook(Mockito.eq("1"), Mockito.any(Book.class))).thenReturn(book);

        mockMvc.perform(MockMvcRequestBuilders.put("/rest/books/1")
                .content("{\"id\": \"\",\"title\": \"Clean Code\",\"author\": {\"id\": \"\",\"firstName\": \"Robert C.\",\"lastName\": \"Martin\"}}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.object.title", Matchers.is("Clean Code")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.object.author.firstName", Matchers.is("Robert C.")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.object.author.lastName", Matchers.is("Martin")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void  testDeleteBook() throws Exception {
        final Author author = new Author();
        author.setFirstName("Robert C.");
        author.setLastName("Martin");
        final Book book = new Book(author, "Clean Code");
        book.setId("1");

        Mockito.when(bookService.deleteBook(Mockito.eq("1"),Mockito.any(Book.class))).thenReturn(book);

        mockMvc.perform(MockMvcRequestBuilders.delete("/rest/books/1")
                .content("{\"id\": \"\",\"title\": \"Clean Code\",\"author\": {\"id\": \"\",\"firstName\": \"Robert C.\",\"lastName\": \"Martin\"}}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.object.title", Matchers.is("Clean Code")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.object.author.firstName", Matchers.is("Robert C.")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.object.author.lastName", Matchers.is("Martin")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
