package com.union.usermanagement.service;

import com.union.usermanagement.dto.UserDTO;
import com.union.usermanagement.dto.UserPutDTO;
import com.union.usermanagement.dto.UserResponseDTO;
import com.union.usermanagement.exception.NoDataFoundException;
import com.union.usermanagement.exception.ValidationException;
import org.springframework.stereotype.Component;

@Component
public interface UserManagementService {

    public UserResponseDTO saveUser(UserDTO userDTO) throws ValidationException;

    public UserResponseDTO getUser(long id) throws NoDataFoundException;

    public UserResponseDTO updateUser(UserPutDTO userPutDTO) throws ValidationException, NoDataFoundException;

    public UserResponseDTO deleteUser(Long id) throws NoDataFoundException;

}
