package com.nissum.test.userservice.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.Instant;

/**
 * ErrorDTO Class
 *
 * @author <a href="mailto:sagitario.wilbert@gmail.com">Wilbert Marcia</a>
 * @version 1.0
 * @since 02-22-2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorDTO<T> {

    @JsonIgnore
    private int status;
    private String message;
    @JsonIgnore
    private Instant timestamp;

}
