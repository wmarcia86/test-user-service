package com.nissum.test.userservice.util.exception;

import com.nissum.test.userservice.model.dto.ErrorDTO;
import com.nissum.test.userservice.util.response.ResponseHandler;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * GlobalExceptionHandler Class
 *
 * @author <a href="mailto:sagitario.wilbert@gmail.com">Wilbert Marcia</a>
 * @version 1.0
 * @since 02-22-2023
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<ErrorDTO> handleUserNotFoundException(UserNotFoundException e) {
        return ResponseHandler.generateErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({RoleNotFoundException.class})
    public ResponseEntity<ErrorDTO> handleRoleNotFoundException(RoleNotFoundException e) {
        return ResponseHandler.generateErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({NotRecordsException.class})
    public ResponseEntity<ErrorDTO> handleNotRecordsException(NotRecordsException e) {
        return ResponseHandler.generateErrorResponse(e.getMessage(), HttpStatus.MULTI_STATUS);
    }

    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<ErrorDTO> handleValidationException(ValidationException e) {
        return ResponseHandler.generateErrorResponse(e.getMessage(), HttpStatus.MULTI_STATUS);
    }

    @ExceptionHandler({UsernameNotFoundException.class})
    public ResponseEntity<ErrorDTO> handleUsernameNotFoundException(UsernameNotFoundException e) {
        return ResponseHandler.generateErrorResponse(e.getMessage(), HttpStatus.MULTI_STATUS);
    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<ErrorDTO> handleAuthenticationException(AuthenticationException e) {
        return ResponseHandler.generateErrorResponse(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({JwtException.class})
    public ResponseEntity<ErrorDTO> handleJwtException(JwtException e) {
        return ResponseHandler.generateErrorResponse(e.getMessage());
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ErrorDTO> handleRuntimeException(RuntimeException e) {
        return ResponseHandler.generateErrorResponse(e.getMessage());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorDTO> handleException(Exception e) {
        return ResponseHandler.generateErrorResponse(e.getMessage());
    }

}
