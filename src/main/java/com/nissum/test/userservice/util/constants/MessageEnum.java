package com.nissum.test.userservice.util.constants;

/**
 * MessageEnum Enum
 *
 * @author <a href="mailto:sagitario.wilbert@gmail.com">Wilbert Marcia</a>
 * @version 1.0
 * @since 02-22-2023
 */
public enum MessageEnum {

    CREATED("User successfully created!"),
    RETRIEVED_ONLY("User successfully retrieved!"),
    RETRIEVED_MULTIPLE("Users successfully retrieved!"),
    UPDATED("User successfully updated!"),
    DELETED("User successfully deleted!"),
    USER_NOT_FOUND("User not found for id %s!"),
    ROLE_NOT_FOUND("Rol not found for name %s!"),
    EMAIL_EXISTS("The email %s already registered!"),
    NOT_RECORDS("No records!"),
    INVALID_EMAIL_FORMAT("Invalid email format for %s"),
    INVALID_PASSORD_FORMAT("Invalid password format for %s"),
    USERNAME_NOT_FOUND("Username invalid!"),
    AUTHENTICATION_ERROR("Invalid email or password!"),
    AUTHENTICATED("Authentication successful!"),
    USER_DESACTIVATED("User Deactivated!");

    private final String message;

    MessageEnum(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }

    public String getMessage() {
        return this.message;
    }

}
