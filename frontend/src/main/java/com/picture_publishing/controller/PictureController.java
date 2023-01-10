package com.picture_publishing.controller;

import com.picture_publishing.dto.UploadPictureRequestDto;
import com.picture_publishing.entities.Picture;
import com.picture_publishing.service.PictureService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("picture")
@CrossOrigin
@Api(tags = "Picture Management API")
public class PictureController {

    private final PictureService pictureService;

    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @PostMapping
    public ResponseEntity<Picture> savePicture(@ModelAttribute @Valid UploadPictureRequestDto pictureRequestDto) throws IOException {

        return new ResponseEntity<>(pictureService.save(pictureRequestDto), HttpStatus.CREATED);
    }
}
