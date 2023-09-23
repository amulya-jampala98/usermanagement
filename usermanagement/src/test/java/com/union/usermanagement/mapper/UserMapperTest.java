package com.union.usermanagement.mapper;

import com.union.usermanagement.dto.UserDTO;
import com.union.usermanagement.dto.UserPutDTO;
import com.union.usermanagement.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserMapperTest {

    @InjectMocks
    private UserMapper userMapper;

    @Test
    public void testUserMapper() {
        userMapper.mapUserModel(mockUser());
        userMapper.mapUpdateUserModel(new User(), mockUpdateUser());
        userMapper.mapUserResponse(mockUserDB());
        userMapper.mapUserResponse(null);
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
}
