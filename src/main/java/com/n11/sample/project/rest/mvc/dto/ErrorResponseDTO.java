package com.n11.sample.project.rest.mvc.dto;

/**
 * Created by mkurucay on 15.02.2017.
 */

/**
 * Used for sending error to client side
 */
public class ErrorResponseDTO {

    private int errorCode;
    private String message;

    public int getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
