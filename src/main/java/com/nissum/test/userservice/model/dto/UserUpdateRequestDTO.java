package com.nissum.test.userservice.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
import java.util.UUID;

/**
 * UserUpdateRequestDTO Class
 *
 * @author <a href="mailto:sagitario.wilbert@gmail.com">Wilbert Marcia</a>
 * @version 1.0
 * @since 02-22-2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequestDTO {

    private String name;
    private String email;
    private String password;
    @JsonProperty("isActive")
    private boolean active;

    List<PhoneUpdateRequestDTO> phones;

}
