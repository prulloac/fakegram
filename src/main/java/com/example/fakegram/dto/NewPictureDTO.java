package com.example.fakegram.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class NewPictureDTO {

    private String username;
    private String description;
    private String location;
    private String url;
    private Set<String> taggedUsers;

}
