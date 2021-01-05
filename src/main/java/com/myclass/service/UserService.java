package com.myclass.service;

import com.myclass.dto.UserDto;

import java.util.List;

/**
 * Author: Nguyễn Chánh Trực
 * Created: 12/25/2020
 * Updated: 12/25/2020
 */
public interface UserService {

    List<UserDto> findAll();

    UserDto findById(int id);

    ServiceResponse checkLogin(String email, String password);

    ServiceResponse deleteById(int id);

    ServiceResponse save(UserDto userDto);

    ServiceResponse update(UserDto userDto);

    ServiceResponse findUserStatisticById(int id);
}
