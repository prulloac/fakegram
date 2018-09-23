package com.example.fakegram.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class BasicUserDataDTO {

    private String username;
    private String biography;
    private Long pictureCount;
    private Long followingCount;
    private Long followersCount;
    private Long picturesTaggedInCount;

}
