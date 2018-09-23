package com.example.fakegram.mapper;

import com.example.fakegram.dto.BasicPictureDataDTO;
import com.example.fakegram.dto.NewPictureDTO;
import com.example.fakegram.dto.PictureDataDTO;
import com.example.fakegram.model.Picture;
import com.example.fakegram.model.User;

import java.util.stream.Collectors;

public class PictureMapper {

    public BasicPictureDataDTO toBasicPictureDataDTO(Picture picture) {
        BasicPictureDataDTO dto = new BasicPictureDataDTO();
        dto.set_id(picture.getId());
        dto.setAuthor(picture.getAuthor().getUsername());
        dto.setCommentsCount((long) picture.getComments().size());
        dto.setDescription(picture.getDescription());
        dto.setQuality(picture.getQuality().name());
        dto.setUrl(picture.getUrl());
        dto.setLikes((long) picture.getLikedBy().size());
        return dto;
    }

    public PictureDataDTO toPictureDataDTO(Picture picture) {
        PictureDataDTO dto = new PictureDataDTO();
        dto.set_id(picture.getId());
        dto.setAuthor(picture.getAuthor().getUsername());
        dto.setCommentsCount((long) picture.getComments().size());
        dto.setDescription(picture.getDescription());
        dto.setQuality(picture.getQuality().name());
        dto.setUrl(picture.getUrl());
        dto.setLikes((long) picture.getLikedBy().size());
        dto.setComments(picture
                .getComments()
                .stream()
                .sorted((o1, o2) -> o2.getModificationTimestamp().compareTo(o1.getModificationTimestamp()))
                .map(x -> new CommentMapper().toCommentDTO(x))
                .collect(Collectors.toList()));
        dto.setHashtags(picture.getHashtags());
        dto.setLocation(picture.getLocation().getName() + ", " + picture.getLocation().getCountry().getName());
        dto.setTagged(picture.getTaggedUsers().stream().map(User::getUsername).collect(Collectors.toSet()));
        dto.setLikedBy(picture.getLikedBy().stream().map(User::getUsername).collect(Collectors.toSet()));
        return dto;
    }

    public Picture fromNewPictureDTO(NewPictureDTO newPictureDTO) {
        Picture picture = new Picture();
        picture.setDescription(newPictureDTO.getDescription());
        picture.setUrl(newPictureDTO.getUrl());
        return picture;
    }
}
