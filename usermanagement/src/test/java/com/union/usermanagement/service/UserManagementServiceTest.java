package com.union.usermanagement.service;

import com.union.usermanagement.dto.UserDTO;
import com.union.usermanagement.dto.UserPutDTO;
import com.union.usermanagement.dto.UserResponseDTO;
import com.union.usermanagement.exception.NoDataFoundException;
import com.union.usermanagement.exception.ValidationException;
import com.union.usermanagement.mapper.UserMapper;
import com.union.usermanagement.model.User;
import com.union.usermanagement.repository.UserRepository;
import com.union.usermanagement.service.impl.UserManagementServiceImpl;
import com.union.usermanagement.validator.UserValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserManagementServiceTest {

    @InjectMocks
    private UserManagementServiceImpl userManagementServiceImpl;

    @Mock
    private UserValidator userValidator;

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserRepository userRepository;

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

    private User mockUserDB() {
        User user = new User();
        user.setFirstName("first");
        user.setLastName("last");
        user.setMobile("1234567890");
        user.setEmail("test@testmail.com");
        user.setJobRole("developer");
        user.setPassword("Pasword&12");
        user.setId(12L);
        return user;
    }

    private UserDTO mockUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("first");
        userDTO.setLastName("last");
        userDTO.setMobile("1234567890");
        userDTO.setEmail("test@testmail.com");
        userDTO.setJobRole("developer");
        userDTO.setPassword("Pasword&12");
        return userDTO;
    }

    private UserPutDTO mockUpdateUser() {
        UserPutDTO userDTO = new UserPutDTO();
        userDTO.setUserId(1L);
        userDTO.setFirstName("first");
        userDTO.setLastName("last");
        userDTO.setMobile("1234567890");
        userDTO.setEmail("test@testmail.com");
        userDTO.setPassword("Pasword&12");
        userDTO.setJobRole("developer");
        return userDTO;
    }

    @Test
    public void testSaveUser() throws ValidationException {
        when(userValidator.validateUser(Mockito.any())).thenReturn(new HashMap<>());
        when(userMapper.mapUserModel(Mockito.any())).thenReturn(mockUserDB());
        when(userRepository.save(Mockito.any(User.class))).thenReturn(mockUserDB());
        when(userMapper.mapUserResponse(Mockito.any())).thenReturn(mockUserResponse());
        assertEquals(userManagementServiceImpl.saveUser(mockUser()).getFirstName(), mockUser().getFirstName());
    }

    @Test(expected = ValidationException.class)
    public void testSaveUserError() throws ValidationException {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error-1", "error");
        when(userValidator.validateUser(Mockito.any())).thenReturn(errorMap);
        when(userMapper.mapUserModel(Mockito.any())).thenReturn(mockUserDB());
        when(userRepository.save(Mockito.any(User.class))).thenReturn(mockUserDB());
        when(userMapper.mapUserResponse(Mockito.any())).thenReturn(mockUserResponse());
        assertEquals(userManagementServiceImpl.saveUser(mockUser()).getFirstName(), mockUser().getFirstName());
    }

    @Test
    public void testUpdateUser() throws ValidationException, NoDataFoundException {
        when(userValidator.validateUpdateUser(Mockito.any())).thenReturn(new HashMap<>());
        when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(mockUserDB()));
        when(userMapper.mapUserModel(Mockito.any())).thenReturn(mockUserDB());
        when(userRepository.save(Mockito.any(User.class))).thenReturn(mockUserDB());
        when(userMapper.mapUserResponse(Mockito.any())).thenReturn(mockUserResponse());
        assertEquals(userManagementServiceImpl.updateUser(mockUpdateUser()).getFirstName(), mockUser().getFirstName());
    }

    @Test(expected = ValidationException.class)
    public void testUpdateUserError() throws ValidationException, NoDataFoundException {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error-1", "error");
        when(userValidator.validateUpdateUser(Mockito.any())).thenReturn(errorMap);
        when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(mockUserDB()));
        when(userMapper.mapUserModel(Mockito.any())).thenReturn(mockUserDB());
        when(userRepository.save(Mockito.any(User.class))).thenReturn(mockUserDB());
        when(userMapper.mapUserResponse(Mockito.any())).thenReturn(mockUserResponse());
        assertEquals(userManagementServiceImpl.updateUser(mockUpdateUser()).getFirstName(), mockUser().getFirstName());
    }

    @Test(expected = NoDataFoundException.class)
    public void testUpdateUserNoData() throws ValidationException, NoDataFoundException {
        when(userValidator.validateUpdateUser(Mockito.any())).thenReturn(new HashMap<>());
        when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        when(userMapper.mapUserModel(Mockito.any())).thenReturn(mockUserDB());
        when(userRepository.save(Mockito.any(User.class))).thenReturn(mockUserDB());
        when(userMapper.mapUserResponse(Mockito.any())).thenReturn(mockUserResponse());
        assertEquals(userManagementServiceImpl.updateUser(mockUpdateUser()).getFirstName(), mockUser().getFirstName());
    }

    @Test
    public void testGetUser() throws NoDataFoundException {
        when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(mockUserDB()));
        when(userMapper.mapUserResponse(Mockito.any())).thenReturn(mockUserResponse());
        assertEquals(userManagementServiceImpl.getUser(1L).getFirstName(), mockUserResponse().getFirstName());
    }

    @Test(expected = NoDataFoundException.class)
    public void testGetUserNoData() throws NoDataFoundException {
        when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        when(userMapper.mapUserResponse(Mockito.any())).thenReturn(mockUserResponse());
        assertEquals(userManagementServiceImpl.getUser(1L).getFirstName(), mockUserResponse().getFirstName());
    }

    @Test
    public void testDeleteUser() throws NoDataFoundException {
        when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(mockUserDB()));
        doNothing().when(userRepository).deleteById(Mockito.anyLong());
        when(userMapper.mapUserResponse(Mockito.any())).thenReturn(mockUserResponse());
        assertEquals(userManagementServiceImpl.deleteUser(1L).getFirstName(), mockUserResponse().getFirstName());
    }

    @Test(expected = NoDataFoundException.class)
    public void testDeleteUserNoData() throws NoDataFoundException {
        when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        doNothing().when(userRepository).deleteById(Mockito.anyLong());
        when(userMapper.mapUserResponse(Mockito.any())).thenReturn(mockUserResponse());
        assertEquals(userManagementServiceImpl.deleteUser(1L).getFirstName(), mockUserResponse().getFirstName());
    }
}
