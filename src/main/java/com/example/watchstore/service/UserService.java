package com.example.watchstore.service;

import com.example.watchstore.dto.UserDto;
import com.example.watchstore.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto> findAllUsers();
}
