package com.picture_publishing.service;

import com.picture_publishing.entities.Picture;
import com.picture_publishing.enums.PictureStatus;
import com.picture_publishing.repository.PictureRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class PictureService {

    private final PictureRepository pictureRepository;

    public PictureService(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    public Picture getPictureById(int id) throws Exception {
        return pictureRepository.findById(id).orElseThrow(() -> new Exception("Picture not found"));
    }

    public List<Picture> getUnProcessPictures() {
        return pictureRepository.findUnProcessPictures();
    }

    @Transactional
    public void updatePictureStatus(PictureStatus pictureStatus, int id) throws Exception {
        Picture pictureById = getPictureById(id);
        if (pictureStatus == PictureStatus.rejected)
            deleteFile(Path.of("uploaded-images/" + id + "/" + pictureById.getAttachment()));
        pictureById.setPictureStatus(pictureStatus);

    }

    private void deleteFile(Path file) {
        if (!Files.isDirectory(file)) {
            try {
                Files.delete(file);
            } catch (IOException ex) {
                System.out.println("Could not delete " + file);
            }
        }
    }

}
