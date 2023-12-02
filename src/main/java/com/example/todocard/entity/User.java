package com.example.todocard.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String age;
    @Column(nullable = false)
    private String user_info;
    @Column(nullable = false)
    private String user_url;

    public User(String username, String password, String name, String age, String user_info, String user_url) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.age=age;
        this.user_info = user_info;
        this.user_url = user_url;
    }


}
