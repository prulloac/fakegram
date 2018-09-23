package com.example.fakegram.mapper;

import com.example.fakegram.dto.AddressDTO;
import com.example.fakegram.model.Address;

public class AddressMapper {

    public AddressDTO toAddressDTO(Address address) {
        if (null == address)
            return null;
        AddressDTO dto = new AddressDTO();
        dto.setStreet(address.getStreet());
        dto.setStreetNumber1(address.getStreetNumber1());
        dto.setStreetNumber2(address.getStreetNumber2());
        dto.setLocation(address.getLocation().getName() + ", " + address.getLocation().getCountry().getName());
        return dto;
    }

    public Address fromAddressDTO(AddressDTO addressDTO) {
        if (null == addressDTO)
            return null;
        Address address = new Address();
        address.setStreet(addressDTO.getStreet());
        address.setStreetNumber1(addressDTO.getStreetNumber1());
        address.setStreetNumber2(addressDTO.getStreetNumber2());
        return address;
    }
}
