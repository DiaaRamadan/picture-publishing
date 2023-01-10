package com.picture_publishing.dto;

import com.picture_publishing.annotiations.ImageType;
import com.picture_publishing.enums.PictureCategory;
import org.springframework.web.multipart.MultipartFile;

public class UploadPictureRequestDto {

    private int userId;

    @ImageType
    private MultipartFile file;

    private String description;

    private PictureCategory pictureCategory;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PictureCategory getPictureCategory() {
        return pictureCategory;
    }

    public void setPictureCategory(PictureCategory pictureCategory) {
        this.pictureCategory = pictureCategory;
    }
}
