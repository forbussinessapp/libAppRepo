package com.n11.sample.project.core.service.exception;

import com.n11.sample.project.core.constant.LibraryMessageCodes;

/**
 * Created by mkurucay on 15.02.2017.
 */
@SuppressWarnings("serial")
public class LibraryException extends RuntimeException  {

    private static final long serialVersionUID = 1L;

    private int code = LibraryMessageCodes.UNKNOWN;
    private String errorMessage;

    public LibraryException() {
        super();
    }

    public LibraryException(String message) {
        super(message);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
