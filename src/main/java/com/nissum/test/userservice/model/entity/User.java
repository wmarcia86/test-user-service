package com.nissum.test.userservice.model.entity;

import com.nissum.test.userservice.model.dto.UserCreateRequestDTO;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * User Class
 *
 * @author <a href="mailto:sagitario.wilbert@gmail.com">Wilbert Marcia</a>
 * @version 1.0
 * @since 02-22-2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "SECURITY", name = "USER")
public class User implements Serializable {

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

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "CREATED", nullable = false)
    private LocalDateTime created;

    @Column(name = "MODIFIED")
    private LocalDateTime modified;

    @Column(name = "LAST_LOGIN")
    private LocalDateTime lastLogin;

    @Column(name = "TOKEN")
    private String token;

    @Column(name = "IS_ACTIVE", nullable = false)
    private boolean active;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(schema = "SECURITY",
               name = "USER_ROLE",
               joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"),
               inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID"))
    private List<Role> roles;

    public User(UserCreateRequestDTO userCreateRequestDTO, String password, String token, List<Role> roles) {
        this.name = userCreateRequestDTO.getName();
        this.email = userCreateRequestDTO.getEmail();
        this.password = password;
        this.created = LocalDateTime.now();
        this.lastLogin = LocalDateTime.now();
        this.token = token;
        this.active = true;
        this.roles = roles;
    }

}
