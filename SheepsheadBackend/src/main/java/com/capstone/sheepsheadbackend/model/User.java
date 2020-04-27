package com.capstone.sheepsheadbackend.model;


import org.springframework.messaging.handler.annotation.Payload;

import java.util.UUID;

public class User {
    private final String uuid;
    private String username;

    public User() {
        this.username = "";
        uuid = UUID.randomUUID().toString();
    }

    public User(String id) {
        this.uuid = id;
    }

    public String getUsername() {
        return username;
    }

    public String getUuid() {
        return uuid;
    }
}
