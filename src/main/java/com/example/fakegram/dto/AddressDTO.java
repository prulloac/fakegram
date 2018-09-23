package com.example.fakegram.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class AddressDTO {

    private String street;
    private String streetNumber1;
    private String streetNumber2;
    private String location;

}
