package com.nissum.test.userservice.util.exception;

/**
 * RoleNotFoundException Class
 *
 * @author <a href="mailto:sagitario.wilbert@gmail.com">Wilbert Marcia</a>
 * @version 1.0
 * @since 02-22-2023
 */
public class RoleNotFoundException extends RuntimeException {

    public RoleNotFoundException() {
        super();
    }

    public RoleNotFoundException(String errorMessage) {
        super(errorMessage);
    }

    public RoleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RoleNotFoundException(Throwable cause) {
        super(cause);
    }

}
