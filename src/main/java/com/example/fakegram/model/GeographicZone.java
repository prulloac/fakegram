package com.example.fakegram.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(
        name = "locations",
        uniqueConstraints = @UniqueConstraint(name = "location_country_uk", columnNames = {"name", "country_id"})
)
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class GeographicZone {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "country_id", foreignKey = @ForeignKey(name = "locations_country_fk"))
    private Country country;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "location")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Picture> pictures;

}
