package com.example.todocard.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommentRequestDtoTest {
    @Test
    @DisplayName("댓글 테스트")
    void test1(){
        CommentRequestDto test  = new CommentRequestDto();
        test.setPostId(1L);
        test.setText("댓글 달기 test");

        System.out.println("test = " + test.getText());
        System.out.println("test.getPostId() = " + test.getPostId());
    }
    
    

}