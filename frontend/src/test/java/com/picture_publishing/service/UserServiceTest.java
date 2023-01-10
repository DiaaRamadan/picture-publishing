package com.picture_publishing.service;

import com.picture_publishing.dto.UserDto;
import com.picture_publishing.entities.User;
import com.picture_publishing.exception.BadRequestException;
import com.picture_publishing.mapper.UserMapper;
import com.picture_publishing.reposiotry.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @Test
    public void createUser() {
        User user = TestHelper.buildUserObject();
        Mockito.when(userRepository.findByEmail(ArgumentMatchers.anyString())).thenReturn(Optional.empty());
        Mockito.when(userRepository.save(user)).thenReturn(user);
        Mockito.when(userService.createUser(user)).thenReturn(userToUserDto(user));

        UserDto createdUser = userService.createUser(user);
        assertThat(createdUser).isNotNull();
        assertThat(createdUser.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    public void createUser_withInvalidEmail() {
        User user = TestHelper.buildUserObject();
        Mockito.when(userRepository.findByEmail(ArgumentMatchers.anyString())).thenReturn(Optional.of(user));
        Assertions.assertThrows(BadRequestException.class, () -> userService.createUser(user));
    }


    private UserDto userToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}