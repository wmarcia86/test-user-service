package com.nissum.test.userservice.repository;

import com.nissum.test.userservice.model.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * PhoneRepository Interface
 *
 * @author <a href="mailto:sagitario.wilbert@gmail.com">Wilbert Marcia</a>
 * @version 1.0
 * @since 02-22-2023
 */
@Repository
public interface PhoneRepository extends JpaRepository<Phone, UUID> {

    /**
     * Find by User Id
     *
     * @param userId
     * @return List<Phone>
     */
    List<Phone> findByUserId(UUID userId);

    /**
     * Delete by User Id
     *
     * @param userId
     */
    void deleteByUserId(UUID userId);

}
