package com.union.usermanagement.mapper;

import com.union.usermanagement.dto.UserDTO;
import com.union.usermanagement.dto.UserPutDTO;
import com.union.usermanagement.dto.UserResponseDTO;
import com.union.usermanagement.model.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    /**
     * Method to map userDTO with user Model
     *
     * @param userDTO
     * @return User
     */
    public User mapUserModel(UserDTO userDTO) {
        User user = new User();
        String salt = BCrypt.gensalt(12);
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setMobile(userDTO.getMobile());
        user.setEmail(userDTO.getEmail());
        user.setPassword(BCrypt.hashpw(userDTO.getPassword(), salt));
        user.setJobRole(userDTO.getJobRole());
        return user;
    }

    /**
     * Method to map userPutDTO with user Model
     *
     * @param user
     * @param userDTO
     * @return User
     */
    public User mapUpdateUserModel(User user, UserPutDTO userDTO) {
        String salt = BCrypt.gensalt(12);
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setMobile(userDTO.getMobile());
        user.setEmail(userDTO.getEmail());
        user.setPassword(BCrypt.hashpw(userDTO.getPassword(), salt));
        user.setJobRole(userDTO.getJobRole());
        return user;
    }

    /**
     * Method to map user with user response
     *
     * @param user
     * @return User
     */
    public UserResponseDTO mapUserResponse(User user) {
        if (null == user) {
            return null;
        }
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setUserId(user.getId());
        userResponseDTO.setFirstName(user.getFirstName());
        userResponseDTO.setLastName(user.getLastName());
        userResponseDTO.setMobile(user.getMobile());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setJobRole(user.getJobRole());
        return userResponseDTO;
    }
}
