package com.n11.sample.project.core.service.exception;

import com.n11.sample.project.core.constant.LibraryMessageCodes;

/**
 * Created by mkurucay on 18.02.2017.
 */
public class NoBookFoundException extends LibraryException {

    private static final String message = "No book found! Please add book to the system.";


    public NoBookFoundException(){
        super(message);
        setCode(LibraryMessageCodes.NO_BOOK_FOUND);
    }
}
