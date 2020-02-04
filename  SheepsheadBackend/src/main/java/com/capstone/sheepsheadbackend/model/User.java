package com.capstone.sheepsheadbackend.model;

import lombok.Data;

@Data
public class User {
    private String firstName;
    private String lastName;
    private String email;
    private int wins;
    private int gamesPlayed;
}
