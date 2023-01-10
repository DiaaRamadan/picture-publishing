package com.picture_publishing.service;

import com.picture_publishing.dto.UploadPictureRequestDto;
import com.picture_publishing.entities.Picture;
import com.picture_publishing.entities.User;
import com.picture_publishing.exception.ResourceNotFoundException;
import com.picture_publishing.reposiotry.PictureRepository;
import com.picture_publishing.reposiotry.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PictureServiceTest {

    @Mock
    private PictureRepository pictureRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PictureService pictureService;

    @Test
    public void save_withInvalidUserId() throws IOException {

        UploadPictureRequestDto pictureRequestDto = TestHelper.buildPictureRequestDto();
        Mockito.when(userRepository.findById(pictureRequestDto.getUserId())).thenReturn(Optional.empty());
        Mockito.doThrow(ResourceNotFoundException.class).when(pictureService).save(pictureRequestDto);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> pictureService.save(pictureRequestDto));
    }

    @Test
    public void save() throws IOException {
        Picture picture = TestHelper.buildPictureObject();
        UploadPictureRequestDto pictureRequestDto = TestHelper.buildPictureRequestDto();
        User user = TestHelper.buildUserObject();
        Mockito.when(userRepository.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.of(user));
        Mockito.when(pictureRepository.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.of(picture));
        Mockito.when(pictureService.save(pictureRequestDto)).thenReturn(picture);
        Mockito.when(pictureRepository.save(picture)).thenReturn(picture);
        Picture savedPicture = pictureService.save(pictureRequestDto);
        assertThat(savedPicture).isNotNull();
    }

    @Test
    public void getAcceptedPictures() {
        List<Picture> pictures = new ArrayList<>();
        pictures.add(TestHelper.buildPictureObject());
        pictures.add(TestHelper.buildPictureObject());
        Mockito.when(pictureRepository.findAcceptedPictures()).thenReturn(pictures);
        List<Picture> acceptedPictures = pictureRepository.findAcceptedPictures();
        assertThat(acceptedPictures.size()).isEqualTo(pictures.size());

    }
}