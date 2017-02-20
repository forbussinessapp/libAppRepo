package com.n11.sample.project.core.service.exception;

import com.n11.sample.project.core.constant.LibraryMessageCodes;

/**
 * Created by mkurucay on 10.02.2017.
 */
public class BookAlreadyExistsException extends LibraryException {

    private static final String message = "Book already exists with this title!";


    public BookAlreadyExistsException(){
        super(message);
        setCode(LibraryMessageCodes.BOOK_ALREADY_EXISTS);
    }
}
