package com.nissum.test.userservice.util.response;

import com.nissum.test.userservice.model.dto.ErrorDTO;
import com.nissum.test.userservice.model.dto.ResponseDTO;
import com.nissum.test.userservice.util.constants.MessageEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;

/**
 * ResponseHandler Class
 *
 * @author <a href="mailto:sagitario.wilbert@gmail.com">Wilbert Marcia</a>
 * @version 1.0
 * @since 02-22-2023
 */
public class ResponseHandler {

    /**
     * Generate Response
     *
     * @param object
     * @return ResponseEntity<Object>
     */
    public static ResponseEntity<Object> generateAuthenticatedResponse(Object object) {
        ResponseDTO response = ResponseDTO.builder()
                .status(HttpStatus.OK.value())
                .message(MessageEnum.AUTHENTICATED.getMessage())
                .data(object).build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Generate Created Response
     *
     * @param object
     * @return ResponseEntity<Object>
     */
    public static ResponseEntity<Object> generateCreatedResponse(Object object) {
        ResponseDTO response = ResponseDTO.builder()
                .status(HttpStatus.CREATED.value())
                .message(MessageEnum.CREATED.getMessage())
                .data(object).build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Generate Retrieved Response
     *
     * @param object
     * @return ResponseEntity<Object>
     */
    public static ResponseEntity<Object> generateRetrievedResponse(Object object, boolean isMultiple) {
        ResponseDTO response = ResponseDTO.builder()
                .status(HttpStatus.OK.value())
                .message(isMultiple ? MessageEnum.RETRIEVED_MULTIPLE.getMessage() : MessageEnum.RETRIEVED_ONLY.getMessage())
                .data(object).build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Generate Updated Response
     *
     * @param object
     * @return ResponseEntity<Object>
     */
    public static ResponseEntity<Object> generateUpdatedResponse(Object object) {
        ResponseDTO response = ResponseDTO.builder()
                .status(HttpStatus.OK.value())
                .message(MessageEnum.UPDATED.getMessage())
                .data(object).build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Generate Deleted Response
     *
     * @param object
     * @return ResponseEntity<Object>
     */
    public static ResponseEntity<Object> generateDeletedResponse(Object object) {
        ResponseDTO response = ResponseDTO.builder()
                .status(HttpStatus.OK.value())
                .message(MessageEnum.DELETED.getMessage())
                .data(object).build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Generate Response
     *
     * @param message
     * @param httpStatus
     * @return ResponseEntity<Object>
     */
    public static ResponseEntity<ErrorDTO> generateErrorResponse(String message, HttpStatus httpStatus) {
        ErrorDTO errorDTO = ErrorDTO.builder().status(httpStatus.value()).message(message).timestamp(Instant.now()).build();

        return new ResponseEntity<>(errorDTO, httpStatus);
    }


    /**
     * Generate Error Response
     *
     * @param message
     * @return ResponseEntity<Object>
     */
    public static ResponseEntity<ErrorDTO> generateErrorResponse(String message) {
        ErrorDTO errorDTO = ErrorDTO.builder().status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(message).timestamp(Instant.now()).build();

        return new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
