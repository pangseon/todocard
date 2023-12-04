package com.example.todocard.entity;

import com.example.todocard.dto.CommentRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommentTest {
    @Test
    @DisplayName("댓글생성")
    void test(){
        String text = "댓글댓글";
        CommentRequestDto test = new CommentRequestDto();
        test.setText(text);

        Comment comment = new Comment(test);

        assertEquals(text,comment.getText());
    }

}