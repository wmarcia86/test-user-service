package com.nissum.test.userservice.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nissum.test.userservice.model.entity.Phone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.time.LocalDateTime;
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
public class PhoneResponseDTO {

    private UUID id;
    private String number;
    @JsonProperty("citycode")
    private String cityCode;
    @JsonProperty("countrycode")
    private String countryCode;
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime created;
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime modified;
    @JsonProperty("isActive")
    private boolean active;

    public PhoneResponseDTO(Phone phone) {
        if (phone != null) {
            this.id = phone.getId();
            this.number = phone.getNumber();
            this.cityCode = phone.getCityCode();
            this.countryCode = phone.getCountryCode();
            this.created = phone.getCreated();
            this.modified = phone.getModified();
            this.active = phone.isActive();
        }
    }

}
