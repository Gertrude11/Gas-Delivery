package com.Gertrude.Final.Project.services;

import com.Gertrude.Final.Project.dto.UserDto;
import com.Gertrude.Final.Project.model.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();

}
