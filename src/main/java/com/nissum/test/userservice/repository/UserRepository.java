package com.nissum.test.userservice.repository;

import com.nissum.test.userservice.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * UserRepository Interface
 *
 * @author <a href="mailto:sagitario.wilbert@gmail.com">Wilbert Marcia</a>
 * @version 1.0
 * @since 02-22-2023
 */
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    /**
     * Find by Email
     *
     * @param email
     * @return  Optional<User>
     */
    Optional<User> findByEmail(String email);

    /**
     * Find By Email Password
     *
     * @param email
     * @param password
     * @return Optional<User>
     */
    @Query("SELECT t FROM User t WHERE t.email = ?1 AND t.password = ?2")
    Optional<User> findByEmailAndPassword(String email, String password);

    /**
     * Exists User by email
     *
     * @param email
     * @return boolean
     */
    boolean existsUserByEmail(String email);

}