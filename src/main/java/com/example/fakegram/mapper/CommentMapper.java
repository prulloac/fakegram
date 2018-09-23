package com.example.fakegram.mapper;

import com.example.fakegram.dto.CommentDTO;
import com.example.fakegram.model.Comment;
import com.example.fakegram.model.User;

import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.stream.Collectors;

public class CommentMapper {

    public CommentDTO toCommentDTO(Comment comment) {
        CommentDTO dto = new CommentDTO();
        dto.setAuthor(comment.getAuthor().getUsername());
        dto.setContent(comment.getContent());
        dto.setTagged(comment.getTaggedUsers().stream().map(User::getUsername).collect(Collectors.toSet()));
        dto.setTimestamp(comment.getModificationTimestamp().format(DateTimeFormatter.ofPattern("dd \\de MMMM \\de yyyy - hh:mm", new Locale("es", "ES"))).toUpperCase());
        return dto;
    }

}
