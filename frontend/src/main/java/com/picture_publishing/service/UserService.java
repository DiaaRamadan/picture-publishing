package com.picture_publishing.service;

import com.picture_publishing.dto.UserDto;
import com.picture_publishing.entities.User;
import com.picture_publishing.enums.UserType;
import com.picture_publishing.exception.BadRequestException;
import com.picture_publishing.mapper.UserMapper;
import com.picture_publishing.reposiotry.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto createUser(User user) {

        Optional<User> userByEmail = userRepository.findByEmail(user.getEmail());
        if (userByEmail.isPresent())
            throw new BadRequestException(String.format("User with email %s already exist", user.getEmail()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setType(UserType.user);
        return userMapper.map(userRepository.save(user));
    }
}
