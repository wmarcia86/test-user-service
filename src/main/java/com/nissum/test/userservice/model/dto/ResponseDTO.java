package com.nissum.test.userservice.model.dto;

import lombok.*;

/**
 * ResponseDTO Class
 *
 * @author <a href="mailto:sagitario.wilbert@gmail.com">Wilbert Marcia</a>
 * @version 1.0
 * @since 02-22-2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDTO<T> {

    private int status;
    private String message;
    private T data;

}
