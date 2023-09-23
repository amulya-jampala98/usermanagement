package com.union.usermanagement.controller;

import com.union.usermanagement.dto.UserDTO;
import com.union.usermanagement.dto.UserPutDTO;
import com.union.usermanagement.dto.UserResponseDTO;
import com.union.usermanagement.exception.NoDataFoundException;
import com.union.usermanagement.exception.ValidationException;
import com.union.usermanagement.service.UserManagementService;
import com.union.usermanagement.validator.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.Instant;

@RestController
@Slf4j
public class UserManagementController {

    @Autowired
    private UserManagementService userManagementService;

    @Autowired
    private UserValidator userValidator;

    /**
     * Method to create user
     *
     * @param userDto
     * @return UserResponseDTO
     * @throws ValidationException
     */
    @PostMapping(value = "/save", produces = "application/json", consumes = "application/json")
    public ResponseEntity<UserResponseDTO> saveUser(@Validated @RequestBody(required = true) UserDTO userDto) throws ValidationException {
        Instant startTime = Instant.now();
        log.info("Inside saveUser controller");
        UserResponseDTO userResponseDTO = userManagementService.saveUser(userDto);
        Instant endTime = Instant.now();
        log.info("Save User method completed successfully took {} ms", Duration.between(startTime, endTime).toMillis());
        return ResponseEntity.ok(userResponseDTO);
    }

    /**
     * Method to get user by userId
     *
     * @param id
     * @return UserResponseDTO
     * @throws NoDataFoundException
     */
    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable(required = true) Long id) throws NoDataFoundException {
        Instant startTime = Instant.now();
        log.info("Inside get user controller");
        UserResponseDTO userResponseDTO = userManagementService.getUser(id);
        Instant endTime = Instant.now();
        log.info("Get User by userId method completed successfully took {} ms", Duration.between(startTime, endTime).toMillis());
        return ResponseEntity.ok(userResponseDTO);
    }

    /**
     * Method to update user data
     *
     * @param userPutDto
     * @return UserResponseDTO
     * @throws ValidationException
     * @throws NoDataFoundException
     */
    @PutMapping(value = "/update", produces = "application/json", consumes = "application/json")
    public ResponseEntity<UserResponseDTO> updateUser(@Validated @RequestBody(required = true) UserPutDTO userPutDto) throws ValidationException, NoDataFoundException {
        Instant startTime = Instant.now();
        log.info("Inside update user controller");
        UserResponseDTO userResponseDTO = userManagementService.updateUser(userPutDto);
        Instant endTime = Instant.now();
        log.info("Update User method completed successfully took {} ms", Duration.between(startTime, endTime).toMillis());
        return ResponseEntity.ok(userResponseDTO);
    }

    /**
     * Method to delete user by userId
     *
     * @param id
     * @return
     * @throws NoDataFoundException
     */
    @DeleteMapping("/user/{id}")
    public ResponseEntity<UserResponseDTO> deleteUserById(@PathVariable(required = true) Long id) throws NoDataFoundException {
        Instant startTime = Instant.now();
        log.info("Inside delete user controller");
        UserResponseDTO userResponseDTO = userManagementService.deleteUser(id);
        Instant endTime = Instant.now();
        log.info("Delete User by userId method completed successfully took {} ms", Duration.between(startTime, endTime).toMillis());
        return ResponseEntity.ok(userResponseDTO);
    }
}
