package com.example.fakegram.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name = "pictures"
)
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Picture {

    @Id
    @GeneratedValue(generator = "PIC_UUID")
    @GenericGenerator(
            name = "PIC_UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "description", length = 1024)
    private String description;

    @ElementCollection
    @CollectionTable(name = "picture_hashtags", joinColumns = {@JoinColumn(name = "picture_id", foreignKey = @ForeignKey(name = "picture_hashtags_picture_fk"))})
    @Column(name = "hashtag")
    private Set<String> hashtags;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "location_picture_location_fk"))
    private GeographicZone location;

    @OneToMany(mappedBy = "picture")
    private Set<Comment> comments = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "pictures_user_fk"))
    private User author;

    @ManyToMany
    @JoinTable(
            name = "picture_tagged_users",
            joinColumns = @JoinColumn(name = "picture_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "picture_tagged_users_picture_fk")),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "picture_tagged_users_user_fk"))
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<User> taggedUsers = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "picture_likes",
            joinColumns = @JoinColumn(name = "picture_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "picture_likes_picture_fk")),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "picture_likes_user_fk"))
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<User> likedBy = new HashSet<>();

    @Column(name = "creation_timestamp", updatable = false, nullable = false)
    private ZonedDateTime creationTimestamp;

    @Column(name = "modification_timestamp")
    private ZonedDateTime modificationTimestamp;

    @Enumerated(EnumType.STRING)
    private PictureQuality quality;

    public Picture() {
        this.creationTimestamp = ZonedDateTime.now();
        this.modificationTimestamp = ZonedDateTime.now();
    }

    public void addLike(User user) {
        this.getLikedBy().add(user);
        user.getPicturesLiked().add(this);
    }
}
