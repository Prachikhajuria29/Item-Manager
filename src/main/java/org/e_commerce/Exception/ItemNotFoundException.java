package org.e_commerce.Exception;

import org.springframework.http.HttpStatus;

public class ItemNotFoundException extends ApplicationException {
    public ItemNotFoundException(String message) {
        super(message, HttpStatus.valueOf(404));
    }
}

