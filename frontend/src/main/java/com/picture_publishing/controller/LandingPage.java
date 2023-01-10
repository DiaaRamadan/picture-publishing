package com.picture_publishing.controller;

import com.picture_publishing.entities.Picture;
import com.picture_publishing.service.PictureService;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@Api(tags = "Landing page Management API")
@RequestMapping("landing-page")
public class LandingPage {

    private final PictureService pictureService;

    public LandingPage(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping("pictures")
    public ResponseEntity<List<Picture>> getAcceptedPictures(){
        return ResponseEntity.ok(pictureService.getAcceptedPictures());
    }

}
