package com.example.fakegram.dao;

import com.example.fakegram.model.GeographicZone;
import com.example.fakegram.model.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface PictureDAO extends JpaRepository<Picture, String> {
    List<Picture> findByAuthorUsernameOrderByCreationTimestamp(String username);
    List<Picture> findByLocationOrderByCreationTimestamp(GeographicZone location);
    List<Picture> findByHashtagsIn(Set<String> hashtags);
}
