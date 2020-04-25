package com.capstone.sheepsheadbackend.model;


import java.util.UUID;

public class User {
    private final String uuid;
    private String username;

    public User(String username) {
        this.username = username;
        uuid = UUID.randomUUID().toString();
    }

    public String getUsername() {
        return username;
    }

    public String getUuid() {
        return uuid;
    }
}
