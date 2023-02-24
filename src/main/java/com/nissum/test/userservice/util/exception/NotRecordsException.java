package com.nissum.test.userservice.util.exception;

/**
 * NotRecordsException Class
 *
 * @author <a href="mailto:sagitario.wilbert@gmail.com">Wilbert Marcia</a>
 * @version 1.0
 * @since 02-22-2023
 */
public class NotRecordsException extends RuntimeException {

    public NotRecordsException() {
        super();
    }

    public NotRecordsException(String errorMessage) {
        super(errorMessage);
    }

    public NotRecordsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotRecordsException(Throwable cause) {
        super(cause);
    }

}
