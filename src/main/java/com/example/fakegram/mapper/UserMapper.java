package com.example.fakegram.mapper;

import com.example.fakegram.dto.BasicUserDataDTO;
import com.example.fakegram.dto.NewUserDataDTO;
import com.example.fakegram.dto.UserDataDTO;
import com.example.fakegram.model.User;
import lombok.extern.slf4j.Slf4j;

import java.util.stream.Collectors;

@Slf4j
public class UserMapper {

    public BasicUserDataDTO toBasicUserDataDTO(User user) {
        BasicUserDataDTO dto = new BasicUserDataDTO();
        dto.setUsername(user.getUsername());
        dto.setBiography(user.getBiography());
        dto.setPictureCount((long) user.getPictures().size());
        dto.setFollowersCount((long) user.getFollowedBy().size());
        dto.setFollowingCount((long) user.getFollowing().size());
        dto.setPicturesTaggedInCount((long) user.getPicturesTaggedIn().size());
        return dto;
    }

    public UserDataDTO toUserDataDTO(User user) {
        UserDataDTO dto = new UserDataDTO();
        dto.set_id(user.getId());
        dto.setUsername(user.getUsername());
        dto.setBiography(user.getBiography());
        dto.setPictureCount((long) user.getPictures().size());
        dto.setAddress(new AddressMapper().toAddressDTO(user.getAddress()));
        dto.setFollowersCount((long) user.getFollowedBy().size());
        dto.setFollowingCount((long) user.getFollowing().size());
        dto.setFollowing(user.getFollowing().stream().map(User::getUsername).collect(Collectors.toSet()));
        dto.setFollowers(user.getFollowedBy().stream().map(User::getUsername).collect(Collectors.toSet()));
        dto.setPictures(user.getPictures()
                .stream()
                .map(x -> new PictureMapper().toBasicPictureDataDTO(x))
                .collect(Collectors.toSet()));
        dto.setPicturesTaggedIn(user.getPicturesTaggedIn()
                .stream()
                .map(x -> new PictureMapper().toBasicPictureDataDTO(x))
                .collect(Collectors.toSet()));
        dto.setLikedPictures(user.getPicturesLiked()
                .stream()
                .map(x -> new PictureMapper().toBasicPictureDataDTO(x))
                .collect(Collectors.toSet()));
        return dto;
    }

    public User fromNewUserDataDTO(NewUserDataDTO newUserData) {
        User user = new User();
        user.setUsername(newUserData.getUsername());
        user.setPassword(newUserData.getPassword());
        user.setEmail(newUserData.getEmail());
        user.setBiography(newUserData.getBiography());
        return user;
    }
}
