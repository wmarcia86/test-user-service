package com.nissum.test.userservice.service;

import com.nissum.test.userservice.model.dto.UserCreateRequestDTO;
import com.nissum.test.userservice.model.dto.UserResponseDTO;
import com.nissum.test.userservice.model.dto.UserUpdateRequestDTO;
import com.nissum.test.userservice.util.exception.NotRecordsException;
import com.nissum.test.userservice.util.exception.UserNotFoundException;
import com.nissum.test.userservice.util.exception.ValidationException;

import java.util.List;
import java.util.UUID;

/**
 * UserService Interface
 *
 * @author <a href="mailto:sagitario.wilbert@gmail.com">Wilbert Marcia</a>
 * @version 1.0
 * @since 02-22-2023
 */
public interface UserService {

    /**
     * Create User
     *
     * @param userCreateRequestDTO
     * @return UserResponseDTO
     * @throws ValidationException
     */
    UserResponseDTO createUser(UserCreateRequestDTO userCreateRequestDTO) throws ValidationException;

    /**
     * Retrieve User by Id
     *
     * @param userId
     * @return UserResponseDTO
     * @throws UserNotFoundException
     */
    UserResponseDTO retrieveUserById(UUID userId) throws UserNotFoundException;

    /**
     * Retrieve All User
     *
     * @return List<UserResponseDTO>
     * @throws NotRecordsException
     */
    List<UserResponseDTO> retrieveAllUser() throws NotRecordsException;

    /**
     * Update User
     *
     * @param userId
     * @param userUpdateRequestDTO
     * @return UserResponseDTO
     * @throws ValidationException
     * @throws UserNotFoundException
     */
    UserResponseDTO updateUser(UUID userId, UserUpdateRequestDTO userUpdateRequestDTO) throws ValidationException, UserNotFoundException;

    /**
     * Delete User by Id
     *
     * @param userId
     * @return int
     * @throws UserNotFoundException
     */
    int deleteUser(UUID userId) throws UserNotFoundException;

    /**
     * Authenticate
     *
     * @param email
     * @param password
     * @return UserResponseDTO
     */
    UserResponseDTO authenticate(String email, String password);

}
