package com.example.fakegram.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CommentDTO {

    private String content;
    private String timestamp;
    private String author;
    private Set<String> tagged;

}
