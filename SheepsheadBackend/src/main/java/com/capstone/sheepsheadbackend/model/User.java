package com.capstone.sheepsheadbackend.model;

import lombok.Data;

import java.util.UUID;

@Data
public class User {
    private String username;
    private String id;

    public User(String name) {
        this.id = UUID.randomUUID().toString();
        this.username = name;
    }
}
