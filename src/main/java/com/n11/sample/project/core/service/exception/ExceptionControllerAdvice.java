package com.n11.sample.project.core.service.exception;

import com.n11.sample.project.rest.mvc.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by mkurucay on 15.02.2017.
 */
@ControllerAdvice
public class ExceptionControllerAdvice {

    private static final String message = "An internal server error occurred!! Please contact your administrator.";

    /**
     *
     * @param ex exception which an unexpected exception in application other than the custom LibraryException
     * @return as error JSON response with a generic error object ErrorResponseDTO with a generic error code and error message
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> exceptionHandler(Exception ex) {
        ErrorResponseDTO error = new ErrorResponseDTO();
        error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage(message);
        return new ResponseEntity<ErrorResponseDTO>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
