package com.union.usermanagement.validator;

import com.union.usermanagement.dto.UserDTO;
import com.union.usermanagement.dto.UserPutDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserValidatorTest {

    @InjectMocks
    private UserValidator userValidator;

    @InjectMocks
    private PasswordValidator passwordValidator;

    private UserPutDTO mockFailUpdateUser() {
        UserPutDTO userDTO = new UserPutDTO();
        userDTO.setFirstName("firstNamefirstNamefirstNamefirstNamefirstNamefirstName");
        userDTO.setLastName("lastNamelastNamelastNamelastNamelastNamelastNamelastName");
        userDTO.setMobile("123456789012");
        userDTO.setEmail("test%^&estmail.com");
        userDTO.setJobRole("developer role developer role developer role developer role developer role developer role");
        userDTO.setPassword("Pasword12");
        userDTO.setUserId(-1L);
        return userDTO;
    }

    private UserDTO mockFailUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("firstNamefirstNamefirstNamefirstNamefirstNamefirstName");
        userDTO.setLastName("lastNamelastNamelastNamelastNamelastNamelastNamelastName");
        userDTO.setMobile("123456789012");
        userDTO.setEmail("test%^&estmail.com");
        userDTO.setJobRole("developer role developer role developer role developer role developer role developer role");
        userDTO.setPassword("Pasword12");
        return userDTO;
    }

    @Test
    public void testValidate() {
        userValidator.validateUser(mockFailUser());
        userValidator.validateUser(new UserDTO());
        userValidator.validateUpdateUser(mockFailUpdateUser());
        userValidator.validateUpdateUser(new UserPutDTO());
        passwordValidator.isValidPassword("pass");
        passwordValidator.isValidPassword("password");
        passwordValidator.isValidPassword("PASSWORD");
        passwordValidator.isValidPassword("-123312344");
        passwordValidator.isValidPassword("Password#123");
    }
}
