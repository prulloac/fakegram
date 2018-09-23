package com.example.fakegram.service;

import com.example.fakegram.dao.PictureDAO;
import com.example.fakegram.dto.BasicPictureDataDTO;
import com.example.fakegram.dto.NewPictureDTO;
import com.example.fakegram.dto.PictureDataDTO;
import com.example.fakegram.mapper.PictureMapper;
import com.example.fakegram.model.GeographicZone;
import com.example.fakegram.model.Picture;
import com.example.fakegram.model.PictureQuality;
import com.example.fakegram.model.User;
import com.example.fakegram.util.HashtagParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PictureService {

    private final PictureDAO pictureDAO;
    private final GeographicZoneService geographicZoneService;
    private final UserService userService;

    @Autowired
    public PictureService(PictureDAO pictureDAO, GeographicZoneService geographicZoneService, UserService userService) {
        this.pictureDAO = pictureDAO;
        this.geographicZoneService = geographicZoneService;
        this.userService = userService;
    }

    public PictureDataDTO postNewPicture(NewPictureDTO newPictureDTO) {
        Picture picture = new PictureMapper().fromNewPictureDTO(newPictureDTO);
        picture.setAuthor(userService.getUserFromUsername(newPictureDTO.getUsername()));
        picture.setLocation(geographicZoneService.getFromLocationString(newPictureDTO.getLocation()));
        if (null != newPictureDTO.getTaggedUsers())
            picture.setTaggedUsers(newPictureDTO.getTaggedUsers()
                    .stream()
                    .map(x -> userService.getOptionalUserFromUsername(x).orElse(null))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet()));
        picture.setQuality(PictureQuality.values()[new Random().nextInt(3)]);
        picture.setDescription(newPictureDTO.getDescription());
        picture.setHashtags(new HashtagParser(newPictureDTO.getDescription()).getHashtags());
        pictureDAO.saveAndFlush(picture);
        return getPicture(picture.getId());
    }

    public PictureDataDTO getPicture(String id) {
        return new PictureMapper().toPictureDataDTO(pictureDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("picture not found")));
    }

    public List<BasicPictureDataDTO> findByUsername(String username) {
        return pictureDAO.findByAuthorUsernameOrderByCreationTimestamp(username)
                .stream()
                .map(x -> new PictureMapper().toBasicPictureDataDTO(x))
                .collect(Collectors.toList());
    }

    public List<BasicPictureDataDTO> findByLocation(String locationString) {
        GeographicZone location = geographicZoneService.getFromLocationString(locationString);
        return pictureDAO.findByLocationOrderByCreationTimestamp(location)
                .stream()
                .map(x -> new PictureMapper().toBasicPictureDataDTO(x))
                .collect(Collectors.toList());
    }

    public List<BasicPictureDataDTO> findByHashtag(String tag) {
        Set<String> hashtag = new HashSet<>();
        hashtag.add(tag);
        return pictureDAO.findByHashtagsIn(new HashSet<>(hashtag))
                .stream()
                .map(x -> new PictureMapper().toBasicPictureDataDTO(x))
                .collect(Collectors.toList());
    }

    public PictureDataDTO likePicture(String pictureId, String userId) {
        Picture picture = pictureDAO.findById(pictureId)
                .orElseThrow(() -> new RuntimeException("picture not found"));
        User user = userService.findById(userId)
                .orElseThrow(() -> new RuntimeException("user not found"));
        if (picture.getLikedBy().contains(user)) {
            picture.getLikedBy().remove(user);
            pictureDAO.saveAndFlush(picture);
            return getPicture(pictureId);
        }
        picture.addLike(user);
        pictureDAO.saveAndFlush(picture);
        return getPicture(pictureId);
    }
}
