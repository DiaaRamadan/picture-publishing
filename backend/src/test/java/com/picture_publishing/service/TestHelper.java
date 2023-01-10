package com.picture_publishing.service;

import com.picture_publishing.entities.Picture;
import com.picture_publishing.entities.User;
import com.picture_publishing.enums.PictureCategory;
import com.picture_publishing.enums.UserType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public final class TestHelper {
    private TestHelper() throws InstantiationException {
        throw new InstantiationException();
    }

    public static User buildUserObject() {
        User user = new User();
        user.setId(1);
        user.setPassword("12345678");
        user.setEmail("test@mail.com");
        user.setType(UserType.user);
        return user;
    }

    public static Picture buildPictureObject() {
        Picture picture = new Picture();
        picture.setUser(buildUserObject());
        picture.setPictureCategory(PictureCategory.machine);
        picture.setDescription("Picture description");
        picture.setAttachment("/file/url/name.jpg");
        picture.setId(1);
        return picture;
    }

}
