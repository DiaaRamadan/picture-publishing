package com.picture_publishing.service;

import com.picture_publishing.entities.Picture;
import com.picture_publishing.enums.PictureStatus;
import com.picture_publishing.repository.PictureRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PictureServiceTest {

    @Mock
    private PictureRepository pictureRepository;

    @InjectMocks
    private PictureService pictureService;

    @Test
    void getPictureById() throws Exception {
        Picture picture = TestHelper.buildPictureObject();
        Mockito.when(pictureRepository.findById(picture.getId())).thenReturn(Optional.of(picture));
        Picture retreivedPicture = pictureService.getPictureById(picture.getId());
        assertThat(retreivedPicture).isNotNull();
    }

    @Test
    void getPictureById_withInvalidId() throws Exception {
        Picture picture = TestHelper.buildPictureObject();
        Mockito.when(pictureRepository.findById(picture.getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(Exception.class, () -> pictureService.getPictureById(picture.getId()));
    }

    @Test
    void getUnProcessPictures() {
        List<Picture> pictures = new ArrayList<>();
        pictures.add(TestHelper.buildPictureObject());
        pictures.add(TestHelper.buildPictureObject());
        Mockito.when(pictureRepository.findUnProcessPictures()).thenReturn(pictures);
        List<Picture> acceptedPictures = pictureRepository.findUnProcessPictures();
        assertThat(acceptedPictures.size()).isEqualTo(pictures.size());
    }

    @Test
    void updatePictureStatus_withAccepted() throws Exception {
        Picture picture = TestHelper.buildPictureObject();
        Mockito.when(pictureRepository.findById(picture.getId())).thenReturn(Optional.of(picture));
        pictureService.updatePictureStatus(PictureStatus.accepted, picture.getId());
        Picture savedPicture = pictureRepository.findById(picture.getId()).get();
        assertThat(picture.getPictureStatus()).isEqualTo(savedPicture.getPictureStatus()).isEqualTo(PictureStatus.accepted);
    }

    @Test
    void updatePictureStatus_withReject() throws Exception {
        Picture picture = TestHelper.buildPictureObject();
        Mockito.when(pictureRepository.findById(picture.getId())).thenReturn(Optional.of(picture));
        pictureService.updatePictureStatus(PictureStatus.rejected, picture.getId());
        Picture savedPicture = pictureRepository.findById(picture.getId()).get();
        assertThat(picture.getPictureStatus()).isEqualTo(savedPicture.getPictureStatus()).isEqualTo(PictureStatus.rejected);
    }
}