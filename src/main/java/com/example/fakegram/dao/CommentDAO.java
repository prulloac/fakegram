package com.example.fakegram.dao;

import com.example.fakegram.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDAO extends JpaRepository<Comment, Long> {
}
