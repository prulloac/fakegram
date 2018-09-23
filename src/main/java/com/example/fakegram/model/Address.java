package com.example.fakegram.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Embeddable
public class Address {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", foreignKey = @ForeignKey(name = "addresses_location_fk"))
    private GeographicZone location;

    @Column(name = "street")
    private String street;

    @Column(name = "streetNumber1")
    private String streetNumber1;

    @Column(name = "streetNumber2")
    private String streetNumber2;

}
