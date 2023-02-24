package com.nissum.test.userservice.model.entity;

import com.nissum.test.userservice.model.dto.PhoneCreateRequestDTO;
import com.nissum.test.userservice.model.dto.PhoneUpdateRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Phone Class
 *
 * @author <a href="mailto:sagitario.wilbert@gmail.com">Wilbert Marcia</a>
 * @version 1.0
 * @since 02-22-2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "SECURITY", name = "PHONE")
public class Phone implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    @Column(name = "ID", nullable = false)
    private UUID id;

    @Column(name = "USER_ID", nullable = false)
    private UUID userId;

    @Column(name = "NUMBER", nullable = false)
    private String number;

    @Column(name = "CITY_CODE", nullable = false)
    private String cityCode;

    @Column(name = "COUNTRY_CODE", nullable = false)
    private String countryCode;

    @Column(name = "CREATED", nullable = false)
    private LocalDateTime created;

    @Column(name = "MODIFIED")
    private LocalDateTime modified;

    @Column(name = "IS_ACTIVE", nullable = false)
    private boolean active;

    public Phone(UUID userId, PhoneCreateRequestDTO phoneCreateRequestDTO) {
        this.userId = userId;
        this.number = phoneCreateRequestDTO.getNumber();
        this.cityCode = phoneCreateRequestDTO.getCityCode();
        this.countryCode = phoneCreateRequestDTO.getCountryCode();
        this.created = LocalDateTime.now();
        this.active = true;
    }
    public Phone(UUID userId, PhoneUpdateRequestDTO phoneUpdateRequestDTO) {
        this.userId = userId;
        this.number = phoneUpdateRequestDTO.getNumber();
        this.cityCode = phoneUpdateRequestDTO.getCityCode();
        this.countryCode = phoneUpdateRequestDTO.getCountryCode();
        this.created = LocalDateTime.now();
        this.active = true;
    }

    public void updateValues(PhoneUpdateRequestDTO phoneUpdateRequestDTO) {
        this.number = phoneUpdateRequestDTO.getNumber();
        this.cityCode = phoneUpdateRequestDTO.getCityCode();
        this.countryCode = phoneUpdateRequestDTO.getCountryCode();
        this.modified = LocalDateTime.now();
        this.active = phoneUpdateRequestDTO.isActive();
    }

}
