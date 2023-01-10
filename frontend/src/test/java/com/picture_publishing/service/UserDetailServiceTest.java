package com.picture_publishing.service;

import com.picture_publishing.entities.User;
import com.picture_publishing.reposiotry.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class UserDetailServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserDetailService userDetailService;

    @Test
    public void loadUserByUsername_withValidEmail() {
        User user = TestHelper.buildUserObject();
        Mockito.when(userRepository.findByEmail(ArgumentMatchers.anyString())).thenReturn(Optional.of(user));
        Mockito.when(userDetailService.loadUserByUsername(ArgumentMatchers.anyString())).thenReturn(userToUserDetails(user));
        UserDetails userDetails = userDetailService.loadUserByUsername("test@Mail.com");
        assertThat(userDetails).isNotNull();
    }

    private UserDetails userToUserDetails(User user) {
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new HashSet<>());
    }

}