package com.nissum.test.userservice.repository;

import com.nissum.test.userservice.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * RoleRepository Interface
 *
 * @author <a href="mailto:sagitario.wilbert@gmail.com">Wilbert Marcia</a>
 * @version 1.0
 * @since 02-22-2023
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    /**
     * Find by Name
     *
     * @param name
     * @return Optional<Role>
     */
    Optional<Role> findByName(String name);

}
