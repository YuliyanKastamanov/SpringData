package com.example.gamestore.entities.users;

public class LoginDTO {

    //TODO: validate email

    private String email;

    private String password;
    public LoginDTO(String[] commandParts) {

        this.email = commandParts[1];
        this.password = commandParts[2];


    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
