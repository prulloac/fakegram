package com.example.fakegram.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(
        name = "countries"
)
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "country")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<GeographicZone> geographicZoneSet;


}
