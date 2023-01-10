package com.picture_publishing.service;

import com.picture_publishing.dto.UploadPictureRequestDto;
import com.picture_publishing.entities.Picture;
import com.picture_publishing.entities.User;
import com.picture_publishing.exception.BadRequestException;
import com.picture_publishing.exception.ResourceNotFoundException;
import com.picture_publishing.reposiotry.PictureRepository;
import com.picture_publishing.reposiotry.UserRepository;
import com.picture_publishing.util.FileUploadUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PictureService {

    private final PictureRepository pictureRepository;
    private final UserRepository userRepository;

    public PictureService(PictureRepository pictureRepository, UserRepository userRepository) {
        this.pictureRepository = pictureRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Picture save(UploadPictureRequestDto pictureRequestDto) throws IOException {

        MultipartFile multipartFile = pictureRequestDto.getFile();
        if (multipartFile.isEmpty()) throw new BadRequestException("Image not provided");

        Optional<User> user = userRepository.findById(pictureRequestDto.getUserId());
        if (user.isEmpty())
            throw new ResourceNotFoundException(String.format("User with Id %s not found", pictureRequestDto.getUserId()));

        Picture picture = new Picture();
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        picture.setAttachment(fileName);
        picture.setPictureCategory(pictureRequestDto.getPictureCategory());
        picture.setDescription(pictureRequestDto.getDescription());
        setImageDimensions(multipartFile, picture);
        picture.setUser(user.get());
        Picture savePicture = pictureRepository.save(picture);
        String dirName = "uploaded-images/" + user.get().getId();
        FileUploadUtil.save(dirName, fileName, multipartFile);

        return savePicture;
    }

    public List<Picture> getAcceptedPictures(){
        return pictureRepository.findAcceptedPictures();
    }

    private void setImageDimensions(MultipartFile multipartFile, Picture picture) throws IOException {
        InputStream inputStream = multipartFile.getInputStream();
        BufferedImage bufferedImage = ImageIO.read(inputStream);
        picture.setHeight(bufferedImage.getHeight());
        picture.setWidth(bufferedImage.getWidth());
    }
}
