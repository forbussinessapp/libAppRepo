package com.n11.sample.project.rest.mvc.controller;

import com.n11.sample.project.core.model.Book;
import com.n11.sample.project.core.service.BookService;
import com.n11.sample.project.core.service.exception.LibraryException;
import com.n11.sample.project.rest.mvc.dto.EMessageStatus;
import com.n11.sample.project.rest.mvc.dto.ErrorResponseDTO;
import com.n11.sample.project.rest.mvc.dto.GenericResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mkurucay on 10.02.2017.
 */
@Controller
@RequestMapping("/rest/books")
public class BookController implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    /**
     *
     * @return  a JSON response which includes headers, configs, HttpStatus.CREATED and a generic object GenericResponseDTO
     * List contains all books data in the database, an integer for response status represented by EMessageStatus.SUCCESS
     * and information string for end users
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<GenericResponseDTO> findAll(){
        final List<Book> books = bookService.getAllBooks();
        GenericResponseDTO response = new GenericResponseDTO(EMessageStatus.SUCCESS, bookService.MESSAGE_FETCH, books);
        return new ResponseEntity<GenericResponseDTO>(response, HttpStatus.OK);
    }

    /**
     *
     * @param book the Book containing the data to be used for creating entity
     * @return a JSON response which includes headers, configs, HttpStatus.CREATED and a generic object GenericResponseDTO
     * contains updated Book data, an integer for response status represented by EMessageStatus.SUCCESS  and information
     * string for end users
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<GenericResponseDTO> createBook(@RequestBody Book book){
        final Book dbBook = bookService.createBook(book);
        GenericResponseDTO response = new GenericResponseDTO(EMessageStatus.SUCCESS, bookService.MESSAGE_CREATE, dbBook);
        return new ResponseEntity<GenericResponseDTO>(response, HttpStatus.CREATED);
    }

    /**
     *
     * @param id  the id of the Book to deleteBook
     * @param book the Book containing the data to be used for updating entity
     * @return  a JSON response which includes headers, configs, HttpStatus.OK and a generic object GenericResponseDTO
     * contains updated Book data, an integer for response status represented by EMessageStatus.SUCCESS  and information
     * string for end users
     */
    @RequestMapping(value="/{id}",method =  RequestMethod.PUT)
    public ResponseEntity<GenericResponseDTO> updateBook(@PathVariable String id, @RequestBody Book book){
        final Book dbBook = bookService.updateBook(id, book);
        GenericResponseDTO response = new GenericResponseDTO(EMessageStatus.SUCCESS, bookService.MESSAGE_UPDATE, dbBook);
        return new ResponseEntity<GenericResponseDTO>(response, HttpStatus.OK);
    }

    /**
     *
     * @param id the id of the Book to deleteBook
     * @param book the Book containing the data to be used for deleting entity
     * @return a JSON response which includes headers, configs, HttpStatus.OK and a generic object GenericResponseDTO which
     * contains deleted Book data, an integer for response status represented by EMessageStatus.SUCCESS  and information
     * string for end users
     */
    @RequestMapping(value="/{id}",method =  RequestMethod.DELETE)
    public ResponseEntity<GenericResponseDTO> deleteBook(@PathVariable String id, @RequestBody Book book){
        final Book dbBook = bookService.deleteBook(id, book);
        GenericResponseDTO response = new GenericResponseDTO(EMessageStatus.SUCCESS, bookService.MESSAGE_DELETE, dbBook);
        return new ResponseEntity<GenericResponseDTO>(response, HttpStatus.OK);
    }

    /**
     *
     * @param ex the custom type LibraryException exception which thrown in any layer of the application
     * @return as error JSON response with a generic error object ErrorResponseDTO with a generic error code and error message
     */
    @ExceptionHandler(LibraryException.class)
    public ResponseEntity<ErrorResponseDTO> exceptionHandler(LibraryException ex) {
        ErrorResponseDTO error = new ErrorResponseDTO();
        error.setErrorCode(ex.getCode());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<ErrorResponseDTO>(error, HttpStatus.PRECONDITION_FAILED);
    }
}
