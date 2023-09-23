package com.union.usermanagement.validator;

import org.springframework.stereotype.Component;

@Component
public class PasswordValidator {

    /**
     * Method to validate password
     *
     * @param password
     * @return boolean
     */
    public static boolean isValidPassword(String password) {

        // Password must be at least 8 characters long
        if (password.length() < 8) {
            return false;
        }
        // Password must contain at least one uppercase letter
        if (!password.matches(".*[A-Z].*")) {
            return false;
        }
        // Password must contain at least one lowercase letter
        if (!password.matches(".*[a-z].*")) {
            return false;
        }
        // Password must contain at least one digit
        if (!password.matches(".*\\d.*")) {
            return false;
        }
        // Password must contain at least one special character
        if (!password.matches(".*[@#$%^&+=].*")) {
            return false;
        }
        // All checks passed, the password is valid
        return true;
    }
}