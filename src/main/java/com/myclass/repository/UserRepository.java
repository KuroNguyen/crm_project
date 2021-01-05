package com.myclass.repository;

import com.myclass.dto.UserDto;
import com.myclass.dto.UserLoginDto;
import com.myclass.entity.User;

import java.util.List;

/**
 * Author: Nguyễn Chánh Trực
 * Created: 12/25/2020
 * Updated: 12/25/2020
 */
public interface UserRepository extends BaseRepository<User,Integer>{

    List<UserDto> findAllJoin();

    UserLoginDto findByEmail(String email);
}
