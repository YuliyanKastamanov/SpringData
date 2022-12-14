package com.example.l09_xmlprocessing.service;

import com.example.l09_xmlprocessing.model.dto.UserSeedDto;
import com.example.l09_xmlprocessing.model.dto.UserViewRootDto;
import com.example.l09_xmlprocessing.model.entity.User;

import java.util.List;

public interface UserService {
    long getCount();

    void seedUsers(List<UserSeedDto> users);

    User getRandomUser();

    UserViewRootDto findUsersWithMoreThanOneSoldProduct();
}
