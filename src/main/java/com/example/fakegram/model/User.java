package com.example.fakegram.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name = "users"
)
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User {

    @Id
    @GeneratedValue(generator = "USER_UUID")
    @GenericGenerator(
            name = "USER_UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", nullable = false, updatable = false)
    private String id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "biography", length = 128)
    private String biography;

    @Embedded
    @AssociationOverride(name = "location", joinColumns = @JoinColumn(name = "address_location", referencedColumnName = "id", foreignKey = @ForeignKey(name = "user_address_location_fk")))
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "address_street")),
            @AttributeOverride(name = "streetNumber1", column = @Column(name = "address_streetnumber_1")),
            @AttributeOverride(name = "streetNumber2", column = @Column(name = "address_streetnumber_2"))
    })
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "author")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Picture> pictures = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "taggedUsers")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Picture> picturesTaggedIn = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "likedBy")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Picture> picturesLiked = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "taggedUsers")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Comment> commentsTaggedIn = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "author")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Comment> comments = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "user_follow",
            joinColumns = @JoinColumn(name = "follower_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "user_follow_follower_fk")),
            inverseJoinColumns = @JoinColumn(name = "following_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "user_follow_following_fk"))
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<User> following = new HashSet<>();

    @ManyToMany(mappedBy = "following")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<User> followedBy = new HashSet<>();

    public void addFollow(User userToFollow) {
        this.getFollowing().add(userToFollow);
        userToFollow.getFollowedBy().add(this);
    }

}
