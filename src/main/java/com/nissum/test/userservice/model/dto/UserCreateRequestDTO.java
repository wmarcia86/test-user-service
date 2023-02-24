package com.nissum.test.userservice.model.dto;

import lombok.*;

import java.util.List;

/**
 * UserCreateRequestDTO Class
 *
 * @author <a href="mailto:sagitario.wilbert@gmail.com">Wilbert Marcia</a>
 * @version 1.0
 * @since 02-22-2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequestDTO {

    private String name;
    private String email;
    private String password;

    List<PhoneCreateRequestDTO> phones;

}
