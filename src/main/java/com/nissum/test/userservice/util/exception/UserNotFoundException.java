package com.nissum.test.userservice.util.exception;

/**
 * UserNotFoundException Class
 *
 * @author <a href="mailto:sagitario.wilbert@gmail.com">Wilbert Marcia</a>
 * @version 1.0
 * @since 02-22-2023
 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String errorMessage) {
        super(errorMessage);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }

}
