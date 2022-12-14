package com.example.l09_xmlprocessing.service.impl;

import com.example.l09_xmlprocessing.model.dto.UserSeedDto;
import com.example.l09_xmlprocessing.model.dto.UserViewRootDto;
import com.example.l09_xmlprocessing.model.dto.UserWithProductDto;
import com.example.l09_xmlprocessing.model.entity.User;
import com.example.l09_xmlprocessing.repository.UserRepository;
import com.example.l09_xmlprocessing.service.UserService;
import com.example.l09_xmlprocessing.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public long getCount() {
        return userRepository.count();
    }

    @Override
    public void seedUsers(List<UserSeedDto> users) {
        users
                .stream()
                .filter(validationUtil::isValid)
                .map(userSeedDto -> modelMapper.map(userSeedDto, User.class))
                .forEach(userRepository::save);
    }

    @Override
    public User getRandomUser() {
        long randomId = ThreadLocalRandom.current().nextLong(1, userRepository.count() + 1);
        return userRepository
                .findById(randomId)
                .orElse(null);
    }

    @Override
    public UserViewRootDto findUsersWithMoreThanOneSoldProduct() {

        UserViewRootDto userViewRootDto = new UserViewRootDto();

        userViewRootDto
                .setProducts(userRepository
                        .findAllUsersWithMoreThanOneSoldProduct()
                        .stream()
                        .map(user -> modelMapper.map(user, UserWithProductDto.class))
                        .collect(Collectors.toList()));

        return userViewRootDto;
    }
}
