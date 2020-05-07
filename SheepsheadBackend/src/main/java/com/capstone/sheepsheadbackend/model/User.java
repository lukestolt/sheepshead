package com.capstone.sheepsheadbackend.model;


import org.springframework.messaging.handler.annotation.Payload;

import java.util.UUID;

public class User {
    private final String uuid;
    private String username;

    /**
     *
     */
    public User() {
        this.username = "";
        uuid = UUID.randomUUID().toString();
    }

    /**
     *
     * @param id
     */
    public User(String id) {
        this.uuid = id;
    }

    /**
     *
     * @param id
     * @param username
     */
    public User(String id, String username){
        this.uuid = id;
        this.username = username;
    }

    /**
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @return
     */
    public String getUuid() {
        return uuid;
    }
}
