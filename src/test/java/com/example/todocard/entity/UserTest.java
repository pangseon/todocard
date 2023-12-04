package com.example.todocard.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    @DisplayName("유저생성")

    void createUser(){
        String username = "TestID";
        String password = "qwerasdf";
        String name = "name";
        String age="age";
        String user_info= "user_info";
        String user_url= "user_url";

        User user = new User(username,password,name,age,user_info,user_url);

        assertEquals(username,user.getUsername());
        assertEquals(password,user.getPassword());
        assertEquals(name,user.getName());
        assertEquals(age,user.getAge());
        assertEquals(user_info,user.getUser_info());
        assertEquals(user_url,user.getUser_url());

    }

}