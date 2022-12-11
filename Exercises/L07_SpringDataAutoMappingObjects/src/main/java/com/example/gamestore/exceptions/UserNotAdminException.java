package com.example.gamestore.exceptions;

public class UserNotAdminException extends RuntimeException {

    public UserNotAdminException(){
        super("Not appropriate rights to add selected game!");
    }
}
