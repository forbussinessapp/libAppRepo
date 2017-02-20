package com.n11.sample.project.core.service.exception;

import com.n11.sample.project.core.constant.LibraryMessageCodes;

/**
 * Created by mkurucay on 10.02.2017.
 */
public class BookNotFoundException extends LibraryException {

    private static final String message = "Book is not found with this title!";

    public BookNotFoundException(){
        super(message);
        setCode(LibraryMessageCodes.BOOK_NOT_FOUND);
    }
}
