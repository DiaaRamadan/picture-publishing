package com.picture_publishing.annotiations;

import com.picture_publishing.exception.BadRequestException;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ImageFileValidator implements ConstraintValidator<ImageType, MultipartFile> {

    @Override
    public void initialize(ImageType constraintAnnotation) {

    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext context) {

        String contentType = multipartFile.getContentType();
        assert contentType != null;
        if (!isSupportedContentType(contentType)) {
            throw new BadRequestException("Only PNG or JPG or GIF images are allowed");
        }

        return true;
    }

    private boolean isSupportedContentType(String contentType) {
        return contentType.equals("image/png") || contentType.equals("image/jpg") || contentType.equals("image/jpeg") || contentType.equals("image/gif");
    }
}