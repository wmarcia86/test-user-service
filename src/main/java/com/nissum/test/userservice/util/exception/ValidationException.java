package com.nissum.test.userservice.util.exception;

/**
 * ValidationException Class
 *
 * @author <a href="mailto:sagitario.wilbert@gmail.com">Wilbert Marcia</a>
 * @version 1.0
 * @since 02-22-2023
 */
public class ValidationException extends Exception {

    public ValidationException() {
        super();
    }

    public ValidationException(String errorMessage) {
        super(errorMessage);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationException(Throwable cause) {
        super(cause);
    }

}
