package com.myclass.service;

import com.myclass.dto.RoleDto;
import com.myclass.dto.UserDto;

import java.util.List;

/**
 * Author: Nguyễn Chánh Trực
 * Created: 12/26/2020
 * Updated: 12/26/2020
 */
public interface RoleService {

    List<RoleDto> findAll();

    RoleDto findById(int id);

    ServiceResponse deleteById(int id);

    ServiceResponse save(RoleDto roleDto);

    ServiceResponse update(RoleDto roleDto);

}
