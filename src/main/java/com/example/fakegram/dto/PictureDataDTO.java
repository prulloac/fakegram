package com.example.fakegram.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PictureDataDTO extends BasicPictureDataDTO {

    private String location;
    private Set<String> hashtags;
    private Set<String> tagged;
    private List<CommentDTO> comments;
    private Set<String> likedBy;

}
