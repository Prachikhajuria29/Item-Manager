package org.e_commerce.Exception;

import org.springframework.http.HttpStatus;

public class DuplicateItemException extends ApplicationException {
    public DuplicateItemException(String message) {
        super(message, HttpStatus.valueOf(409));
    }
}
