package com.example.todocard.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserResponseDtoTest {

    @Test
    @DisplayName("UserResponse")
    void test1(){
        UserResponseDto test = new UserResponseDto();
        test.getAge();
        test.getUser_url();
        test.getUser_info();
    }

}