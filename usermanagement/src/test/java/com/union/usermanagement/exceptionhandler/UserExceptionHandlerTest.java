package com.union.usermanagement.exceptionhandler;

import com.union.usermanagement.exception.ValidationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserExceptionHandlerTest {

    @InjectMocks
    private UserExceptionHandler userExceptionHandler;

    @InjectMocks
    private ValidationException validationException;

    @Test
    public void testUserExceptionHandler() {
        ValidationException validationException = new ValidationException("Error", new HashMap<>());
        userExceptionHandler.handleValidationException(validationException);
    }

    @Test
    public void testMyException() {
        ValidationException exception = new ValidationException("error", new HashMap<>());
        assertEquals(exception.getErrorMap(), new HashMap<>());

    }
}
