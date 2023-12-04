package com.example.todocard.dto;

import com.example.todocard.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRequestDtoTest {
    @Test
    @DisplayName("유저 회원가입 테스트")
    void test1(){
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setUser_info("testcode");
        userRequestDto.setUser_url("test깃허브");
        userRequestDto.setName("test중");
        userRequestDto.setUsername("testid123");
        userRequestDto.setPassword("passwordtes");
        userRequestDto.setAge("23");

        System.out.println("userRequestDto = " + userRequestDto.getUser_url());
        System.out.println("userRequestDto = " + userRequestDto.getUser_info());
        System.out.println(" = " + userRequestDto.getName());
        userRequestDto.getAge();
        userRequestDto.getUser_info();
        userRequestDto.getUsername();
    }

}