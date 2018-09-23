package com.example.fakegram.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserDataDTO extends BasicUserDataDTO {

    private String _id;
    private AddressDTO address;
    private Set<BasicPictureDataDTO> pictures;
    private Set<BasicPictureDataDTO> picturesTaggedIn;
    private Set<String> following;
    private Set<String> followers;
    private Set<BasicPictureDataDTO> likedPictures;

}
