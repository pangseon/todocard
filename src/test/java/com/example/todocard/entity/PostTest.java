package com.example.todocard.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostTest {
    @Test
    @DisplayName("할일 게시판 생성")
    void test(){
        String title= "test";
        String content = "testcontent";
        Post post =new Post(title,content);
        assertEquals(title,post.getTitle());
        assertEquals(content,post.getContent());
    }

}