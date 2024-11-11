package service;

import dataaccess.DataAccessException;

public class InvalidUserException extends DataAccessException {
    public InvalidUserException(String message) {
        super(message);
    }
}
