package com.example.fakegram.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class BasicPictureDataDTO {

    private String _id;
    private String author;
    private String description;
    private String url;
    private String quality;
    private Long commentsCount;
    private Long likes;

}
