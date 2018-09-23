package com.example.fakegram.controller;

import com.example.fakegram.dto.NewPictureDTO;
import com.example.fakegram.service.PictureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(value = "pictures")
public class PictureController {

    private final PictureService pictureService;

    @Autowired
    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity postNewPicture(
            @RequestBody NewPictureDTO newPictureDTO
    ) {
        try {
            return new ResponseEntity<>(pictureService.postNewPicture(newPictureDTO), HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public ResponseEntity getPicturesFromUser(
            @RequestParam("username") String username
    ) {
        try {
            return new ResponseEntity<>(pictureService.findByUsername(username), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "location", method = RequestMethod.GET)
    public ResponseEntity getPicturesFromLocation(
            @RequestParam("location") String location) {
        try {
            return new ResponseEntity<>(pictureService.findByLocation(location), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getPicture(
            @RequestParam("id") String id
    ) {
        try {
            return new ResponseEntity<>(pictureService.getPicture(id), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "hashtag", method = RequestMethod.GET)
    public ResponseEntity getPicturesFromHashtag(
            @RequestParam("tag") String hashtag
    ) {
        try {
            return new ResponseEntity<>(pictureService.findByHashtag(hashtag), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "like", method = RequestMethod.POST)
    public ResponseEntity likePicture(
            @RequestParam("picture_id") String pictureId,
            @RequestParam("user_id") String userId
    ) {
        try {
            return new ResponseEntity<>(pictureService.likePicture(pictureId, userId), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
