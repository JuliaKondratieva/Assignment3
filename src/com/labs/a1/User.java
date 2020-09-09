package com.labs.a1;

import java.util.UUID;

public class User {
    private String username;
    private UUID idNumber;

    public User() {
        username = "";
        idNumber= UUID.randomUUID();
    }

    public User(String username) {
        this.username = username;
    }

    public UUID getIdNumber() {
        return idNumber;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username=username;
    }

}
