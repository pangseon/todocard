package com.example.todocard.dto;

import com.example.todocard.entity.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

class CommentResponseDtoTest {

    @Test
    @Rollback(value = false)
    void test1(){
        Comment comment = new Comment();

        CommentResponseDto test = new CommentResponseDto(comment);
        System.out.println("test.getText() = " + test.getContent());
        System.out.println("test.getPostId() = " + test.getUser());
        System.out.println("test.getCreateDate() = " + test.getCreateDate());
    }
}