package com.example.l08_jsonprocessing.service;

import com.example.l08_jsonprocessing.model.dto.UserSoldDto;
import com.example.l08_jsonprocessing.model.entity.User;

import java.io.IOException;
import java.util.List;

public interface UserService {
    void seedUsers() throws IOException;

    User findRandomUser();

    List<UserSoldDto> findAllUsersWithMoreThanOneSoldProduct();
}
