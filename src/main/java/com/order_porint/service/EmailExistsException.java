package com.order_porint.service;

/**
 * Created by zsx on 2018-09-05.
 */
public class EmailExistsException extends Exception {

    public EmailExistsException(String message) {
        super(message);
    }

    public EmailExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
