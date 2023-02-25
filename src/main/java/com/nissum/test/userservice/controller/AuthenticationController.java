package com.nissum.test.userservice.controller;

import com.nissum.test.userservice.service.UserService;
import com.nissum.test.userservice.util.constants.MessageEnum;
import com.nissum.test.userservice.util.exception.ValidationException;
import com.nissum.test.userservice.util.response.ResponseHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * AuthenticationController Class
 *
 * @author <a href="mailto:sagitario.wilbert@gmail.com">Wilbert Marcia</a>
 * @version 1.0
 * @since 02-22-2023
 */
@RestController
@RequestMapping("/auth")
@Api(tags = "Authentication")
public class AuthenticationController {

    @Autowired
    UserService userService;

    // Create User
    @ApiOperation(value = "This method is used to login")
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestParam(required = true) String email,
                                        @RequestParam(required = true) String password) throws ValidationException {

        return ResponseHandler.generateResponse(MessageEnum.AUTHENTICATED.getMessage(), HttpStatus.OK,
                userService.authenticate(email, password));
    }

}
