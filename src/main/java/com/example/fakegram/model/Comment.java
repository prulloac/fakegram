package com.example.fakegram.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Set;

@Entity
@Table(
        name = "comments"
)
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "content", length = 1024)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id", updatable = false, nullable = false, foreignKey = @ForeignKey(name = "comments_user_fk"))
    private User author;

    @ManyToOne
    @JoinColumn(name = "picture_id", updatable = false, nullable = false, foreignKey = @ForeignKey(name = "comments_picture_fk"))
    private Picture picture;

    @ManyToMany
    @JoinTable(
            name = "comment_tagged_users",
            joinColumns = @JoinColumn(name = "comment_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "comment_tagged_users_comment_fk")),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "comment_tagged_users_user_fk"))
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<User> taggedUsers;

    @Column(name = "creation_timestamp", updatable = false, nullable = false)
    private ZonedDateTime creationTimestamp;

    @Column(name = "modification_timestamp")
    private ZonedDateTime modificationTimestamp;

    public Comment() {
        creationTimestamp = ZonedDateTime.now();
        modificationTimestamp = ZonedDateTime.now();
    }
}
