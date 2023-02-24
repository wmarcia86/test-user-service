package com.nissum.test.userservice.controller;

import com.nissum.test.userservice.model.dto.UserCreateRequestDTO;
import com.nissum.test.userservice.model.dto.UserUpdateRequestDTO;
import com.nissum.test.userservice.service.UserService;
import com.nissum.test.userservice.util.exception.UserNotFoundException;
import com.nissum.test.userservice.util.exception.ValidationException;
import com.nissum.test.userservice.util.response.ResponseHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * UserController Class
 *
 * @author <a href="mailto:sagitario.wilbert@gmail.com">Wilbert Marcia</a>
 * @version 1.0
 * @since 02-22-2023
 */
@RestController
@RequestMapping(value = "/api/users")
@Api(tags = "Users")
public class UserController {

    @Autowired
    UserService userService;

    // Create User
    @ApiOperation(value = "This method is used to Create User")
    @PostMapping("/")
    public ResponseEntity<Object> createUser(@RequestBody UserCreateRequestDTO userCreateRequestDTO) throws ValidationException {
        return ResponseHandler.generateCreatedResponse(userService.createUser(userCreateRequestDTO));
    }

    // Retrieve User by Id
    @ApiOperation(value = "This method is used to Retrieve User by Id")
    @GetMapping(value = "/{userId}")
    public ResponseEntity<Object> retrieveUserById(@PathVariable UUID userId) throws UserNotFoundException {
        return ResponseHandler.generateRetrievedResponse(userService.retrieveUserById(userId), false);
    }

    // Retrieve All User
    @ApiOperation(value = "This method is used to Retrieve All User")
    @GetMapping(value = "/")
    public ResponseEntity<Object> retrieveAllUser() throws UserNotFoundException {
        return ResponseHandler.generateRetrievedResponse(userService.retrieveAllUser(), true);
    }

    // Update User
    @ApiOperation(value = "This method is used to Update User")
    @PutMapping("/{userId}")
    public ResponseEntity<Object> updateUser(@PathVariable UUID userId, @RequestBody UserUpdateRequestDTO userUpdateRequestDTO)
            throws ValidationException, UserNotFoundException {
        return ResponseHandler.generateUpdatedResponse(userService.updateUser(userId, userUpdateRequestDTO));
    }

    // Delete User
    @ApiOperation(value = "This method is used to Delete User")
    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable UUID userId) throws UserNotFoundException {
        return ResponseHandler.generateDeletedResponse(userService.deleteUser(userId));
    }

}
