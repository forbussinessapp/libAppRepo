package com.n11.sample.project.rest.mvc.dto;

import com.n11.sample.project.core.model.Book;

/**
 * Created by mkurucay on 17.02.2017.
 */

/**
 * Used for sending response to client side
 */
public class GenericResponseDTO {

    private Integer status;

    private String message;

    private Object object;

    public GenericResponseDTO() {

    }

    public GenericResponseDTO(EMessageStatus status) {
        super();
        this.status = status.ordinal();
    }

    public GenericResponseDTO(EMessageStatus status, String message) {
        super();
        this.status = status.ordinal();
        this.message = message;
    }

    public GenericResponseDTO(EMessageStatus status, String message,Object object) {
        super();
        this.status = status.ordinal();
        this.message = message;
        this.object = object;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(EMessageStatus status) {
        this.status = status.ordinal();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
