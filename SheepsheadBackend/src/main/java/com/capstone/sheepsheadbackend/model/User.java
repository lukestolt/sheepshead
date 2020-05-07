package com.capstone.sheepsheadbackend.model;


import org.springframework.messaging.handler.annotation.Payload;

import java.util.UUID;

public class User {
    private final String uuid;
    private String username;

    /**
     * Create an empty user
     */
    public User() {
        this.username = "";
        uuid = UUID.randomUUID().toString();
    }

    /**
     * Create User with given Id
     * @param id
     */
    public User(String id) {
        this.uuid = id;
    }

    /**
     * Create new User with given Id and username
     * @param id Id of user
     * @param username Username of user
     */
    public User(String id, String username){
        this.uuid = id;
        this.username = username;
    }

    /**
     * Get username of user
     * @return username of user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Get Id of User
     * @return Id of user
     */
    public String getUuid() {
        return uuid;
    }
}
