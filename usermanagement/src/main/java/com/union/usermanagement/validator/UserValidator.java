package com.union.usermanagement.validator;

import com.union.usermanagement.dto.UserDTO;
import com.union.usermanagement.dto.UserPutDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Component
public class UserValidator {

    /**
     * Method to validate user
     *
     * @param userDTO
     * @return Map<String, String>
     */
    public Map<String, String> validateUser(UserDTO userDTO) {
        Map<String, String> errorMap = new HashMap<>();
        validateFirstName(userDTO.getFirstName(), errorMap);
        validateLastName(userDTO.getLastName(), errorMap);
        validateMobile(userDTO.getMobile(), errorMap);
        validateEmail(userDTO.getEmail(), errorMap);
        validateJobRole(userDTO.getJobRole(), errorMap);
        validatePassword(userDTO.getPassword(), errorMap);
        return errorMap;
    }

    /**
     * Method to validate update user
     *
     * @param userPutDTO
     * @return Map<String, String>
     */
    public Map<String, String> validateUpdateUser(UserPutDTO userPutDTO) {
        Map<String, String> errorMap = new HashMap<>();
        validateFirstName(userPutDTO.getFirstName(), errorMap);
        validateLastName(userPutDTO.getLastName(), errorMap);
        validateMobile(userPutDTO.getMobile(), errorMap);
        validateEmail(userPutDTO.getEmail(), errorMap);
        validateJobRole(userPutDTO.getJobRole(), errorMap);
        validatePassword(userPutDTO.getPassword(), errorMap);
        if ((Objects.nonNull(userPutDTO.getUserId()))) {
            validateUserId(userPutDTO.getUserId(), errorMap);
        } else {
            errorMap.put("Error-1007", "User id cannot be null");
        }
        return errorMap;
    }

    /**
     * Method to validate firstName
     *
     * @param firstName
     * @param errorMap
     */
    private void validateFirstName(String firstName, Map<String, String> errorMap) {
        if (null != firstName && !firstName.isEmpty()) {
            if (firstName.length() > 50) {
                errorMap.put("Error-1001", "firstName size should be between 1 and 50");
            }
        } else {
            errorMap.put("Error-1001", "firstName cannot be null");
        }
    }

    /**
     * Method to validate lastName
     *
     * @param lastName
     * @param errorMap
     */
    private void validateLastName(String lastName, Map<String, String> errorMap) {
        if (null != lastName && !lastName.isEmpty()) {
            if (lastName.length() > 50) {
                errorMap.put("Error-1002", "lastName size should be between 1 and 50");
            }
        } else {
            errorMap.put("Error-1002", "lastName cannot be null");
        }
    }

    /**
     * Method to validate lastName
     *
     * @param mobile
     * @param errorMap
     */
    private void validateMobile(String mobile, Map<String, String> errorMap) {
        if (null != mobile && !mobile.isEmpty()) {
            String pattern = "^\\d{10}$";
            if (!mobile.matches(pattern)) {
                errorMap.put("Error-1003", "mobile number size should be between 1 and 10");
            }
        } else {
            errorMap.put("Error-1003", "mobile number cannot be null");
        }
    }

    /**
     * Method to validate lastName
     *
     * @param email
     * @param errorMap
     */
    private void validateEmail(String email, Map<String, String> errorMap) {
        if (null != email && !email.isEmpty()) {
            String pattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
            if (!email.matches(pattern)) {
                errorMap.put("Error-1004", "email should be in a valid format");
            }
        } else {
            errorMap.put("Error-1004", "email cannot be null");
        }
    }

    /**
     * Method to validate lastName
     *
     * @param jobRole
     * @param errorMap
     */
    private void validateJobRole(String jobRole, Map<String, String> errorMap) {
        if (null != jobRole && !jobRole.isEmpty()) {
            if (jobRole.length() > 50) {
                errorMap.put("Error-1005", "jobRole size should be between 1 and 50");
            }
        } else {
            errorMap.put("Error-1005", "jobRole cannot be null");
        }
    }

    /**
     * Method to validate lastName
     *
     * @param password
     * @param errorMap
     */
    private void validatePassword(String password, Map<String, String> errorMap) {
        if (null != password && !password.isEmpty()) {
            if (!PasswordValidator.isValidPassword(password)) {
                errorMap.put("Error-1006", "Password should be alphanumeric with special characters");
            }
        } else {
            errorMap.put("Error-1006", "Password cannot be null");
        }
    }

    /**
     * Method to validate lastName
     *
     * @param id
     * @param errorMap
     */
    private void validateUserId(long id, Map<String, String> errorMap) {
        if (id <= 0) {
            errorMap.put("Error-1007", "User id should be a valid number");
        }
    }
}
