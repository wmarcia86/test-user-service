package com.nissum.test.userservice.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nissum.test.userservice.model.entity.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * UserResponseDTO Class
 *
 * @author <a href="mailto:sagitario.wilbert@gmail.com">Wilbert Marcia</a>
 * @version 1.0
 * @since 02-22-2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseDTO {

    private UUID id;
    private String name;
    private String email;
    private String password;
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime created;
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime modified;
    @JsonProperty("last_Login")
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime lastLogin;
    private String token;
    @JsonProperty("isActive")
    private boolean active;

    private List<PhoneResponseDTO> phones;

    public UserResponseDTO(User user, boolean isLoadAllField) {
        if (user != null) {
            this.id = user.getId();
            this.created = user.getCreated();
            this.modified = user.getModified();
            this.lastLogin = user.getLastLogin();
            this.token = user.getToken();
            this.active = user.isActive();

            if (isLoadAllField) {
                this.name = user.getName();
                this.email = user.getEmail();
                this.password = user.getPassword();
            }
        }
    }

}
