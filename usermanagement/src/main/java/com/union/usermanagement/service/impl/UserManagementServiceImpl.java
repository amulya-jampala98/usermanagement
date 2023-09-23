package com.union.usermanagement.service.impl;

import com.union.usermanagement.dto.UserDTO;
import com.union.usermanagement.dto.UserPutDTO;
import com.union.usermanagement.dto.UserResponseDTO;
import com.union.usermanagement.exception.NoDataFoundException;
import com.union.usermanagement.exception.ValidationException;
import com.union.usermanagement.mapper.UserMapper;
import com.union.usermanagement.model.User;
import com.union.usermanagement.repository.UserRepository;
import com.union.usermanagement.service.UserManagementService;
import com.union.usermanagement.validator.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
@Slf4j
public class UserManagementServiceImpl implements UserManagementService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserRepository userRepository;

    /**
     * Method to save user
     *
     * @param userDTO
     * @return UserResponseDTO
     * @throws ValidationException
     */
    @Override
    public UserResponseDTO saveUser(UserDTO userDTO) throws ValidationException {
        Map<String, String> errorMap = userValidator.validateUser(userDTO);
        if (!errorMap.isEmpty()) {
            throw new ValidationException("Validation exception occurred", errorMap);
        }
        User user = userMapper.mapUserModel(userDTO);
        UserResponseDTO userResponseDTO = userMapper.mapUserResponse(userRepository.save(user));
        return userResponseDTO;
    }

    /**
     * Method to get user by user id
     *
     * @param id
     * @return UserResponseDTO
     * @throws NoDataFoundException
     */
    @Override
    public UserResponseDTO getUser(long id) throws NoDataFoundException {
        return userMapper.mapUserResponse(userRepository.findById(id).orElseThrow(() -> new NoDataFoundException()));
    }

    /**
     * Method to update user
     *
     * @param userPutDTO
     * @return UserResponseDTO
     * @throws ValidationException
     * @throws NoDataFoundException
     */
    @Override
    public UserResponseDTO updateUser(UserPutDTO userPutDTO) throws ValidationException, NoDataFoundException {
        Map<String, String> errorMap = userValidator.validateUpdateUser(userPutDTO);
        if (!errorMap.isEmpty()) {
            throw new ValidationException("Validation exception occurred", errorMap);
        }
        Optional<User> existingUser = Optional.ofNullable(userRepository.findById(userPutDTO.getUserId()).orElseThrow(() -> new NoDataFoundException()));
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            userMapper.mapUpdateUserModel(user, userPutDTO);
            return userMapper.mapUserResponse(userRepository.save(user));
        }
        return new UserResponseDTO();
    }

    /**
     * Method to delete user by user id
     *
     * @param id
     * @return UserResponseDTO
     * @throws NoDataFoundException
     */
    @Override
    public UserResponseDTO deleteUser(Long id) throws NoDataFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new NoDataFoundException());
        userRepository.deleteById(id);
        return userMapper.mapUserResponse(user);
    }
}
