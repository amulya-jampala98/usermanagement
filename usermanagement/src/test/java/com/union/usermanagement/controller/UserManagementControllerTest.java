package com.union.usermanagement.controller;

import com.union.usermanagement.dto.UserDTO;
import com.union.usermanagement.dto.UserPutDTO;
import com.union.usermanagement.dto.UserResponseDTO;
import com.union.usermanagement.exception.NoDataFoundException;
import com.union.usermanagement.exception.ValidationException;
import com.union.usermanagement.service.UserManagementService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserManagementControllerTest {

    @InjectMocks
    private UserManagementController userManagementController;

    @Mock
    private UserManagementService userManagementService;

    @Test
    public void testSaveUser() throws ValidationException {
        when(userManagementService.saveUser(Mockito.any())).thenReturn(mockUserResponse());
        assertEquals(userManagementController.saveUser(new UserDTO()).getStatusCode().value(), 200);
    }

    @Test(expected = ValidationException.class)
    public void testSaveUserEx() throws ValidationException {
        when(userManagementService.saveUser(Mockito.any())).thenThrow(ValidationException.class);
        userManagementController.saveUser(new UserDTO()).getStatusCode().value();
    }

    @Test
    public void testUpdateUser() throws ValidationException, NoDataFoundException {
        when(userManagementService.updateUser(Mockito.any())).thenReturn(mockUserResponse());
        assertEquals(userManagementController.updateUser(new UserPutDTO()).getStatusCode().value(), 200);
    }

    @Test(expected = ValidationException.class)
    public void testUpdateUserEx() throws ValidationException, NoDataFoundException {
        when(userManagementService.updateUser(Mockito.any())).thenThrow(ValidationException.class);
        userManagementController.updateUser(new UserPutDTO()).getStatusCode().value();
    }

    @Test(expected = NoDataFoundException.class)
    public void testUpdateUserNoDataEx() throws ValidationException, NoDataFoundException {
        when(userManagementService.updateUser(Mockito.any())).thenThrow(NoDataFoundException.class);
        userManagementController.updateUser(new UserPutDTO()).getStatusCode().value();
    }

    @Test
    public void testGetUser() throws NoDataFoundException {
        when(userManagementService.getUser(Mockito.anyLong())).thenReturn(mockUserResponse());
        assertEquals(userManagementController.getUserById(1L).getStatusCode().value(), 200);
    }

    @Test(expected = NoDataFoundException.class)
    public void testGetUserEx() throws NoDataFoundException {
        when(userManagementService.getUser(Mockito.anyLong())).thenThrow(NoDataFoundException.class);
        userManagementController.getUserById(1L);
    }

    @Test
    public void testDeleteUser() throws NoDataFoundException {
        when(userManagementService.deleteUser(Mockito.anyLong())).thenReturn(mockUserResponse());
        assertEquals(userManagementController.deleteUserById(1L).getStatusCode().value(), 200);
    }

    @Test(expected = NoDataFoundException.class)
    public void testDeleteUserEx() throws NoDataFoundException {
        when(userManagementService.deleteUser(Mockito.anyLong())).thenThrow(NoDataFoundException.class);
        userManagementController.deleteUserById(1L);
    }

    private UserResponseDTO mockUserResponse() {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setUserId(1L);
        userResponseDTO.setFirstName("first");
        userResponseDTO.setLastName("last");
        userResponseDTO.setMobile("1234567890");
        userResponseDTO.setEmail("test@testmail.com");
        userResponseDTO.setJobRole("developer");
        return userResponseDTO;
    }
}
