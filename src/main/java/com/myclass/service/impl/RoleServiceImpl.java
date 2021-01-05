package com.myclass.service.impl;

import com.myclass.common.StatusCode;
import com.myclass.dto.RoleDto;
import com.myclass.dto.UserDto;
import com.myclass.entity.Role;
import com.myclass.repository.RoleRepository;
import com.myclass.service.RoleService;
import com.myclass.service.ServiceResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Nguyễn Chánh Trực
 * Created: 12/26/2020
 * Updated: 12/26/2020
 */
@Component
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<RoleDto> findAll() {
        List<Role> roles = roleRepository.findAll();
        List<RoleDto> roleDtos = new ArrayList<>();
        for (Role role:roles) {
            RoleDto roleDto = new RoleDto(role.getId(), role.getName(), role.getDescription());
            roleDtos.add(roleDto);
        }
        return roleDtos;
    }

    @Override
    public RoleDto findById(int id) {
        Role role = roleRepository.findById(id);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(role, RoleDto.class);
    }

    @Override
    public ServiceResponse deleteById(int id) {
        // Check existence
        if (roleRepository.findById(id) == null) {
            return new ServiceResponse(StatusCode.FAILED,"Không tồn tại");
        }

        if (roleRepository.deleteById(id)) {
            return new ServiceResponse(StatusCode.SUCCESS,"Thành Công");
        }
        return new ServiceResponse(StatusCode.FAILED,"Thất bại");
    }

    @Override
    public ServiceResponse save(RoleDto roleDto) {
        // Check existence
        if (roleRepository.findByName(roleDto.getName()) != null) {
            return new ServiceResponse(StatusCode.FAILED,"Quyền đã tồn tại");
        }
        ModelMapper modelMapper = new ModelMapper();
        Role role = modelMapper.map(roleDto, Role.class);
        if (roleRepository.saveOrUpdate(role)) {
            return new ServiceResponse(StatusCode.SUCCESS,"Thành Công");
        }
        return new ServiceResponse(StatusCode.FAILED,"Thêm quyền thất bại");
    }

    @Override
    public ServiceResponse update(RoleDto roleDto) {
        // Check existence first
        if (roleRepository.findById(roleDto.getId()) == null) {
            return new ServiceResponse(StatusCode.FAILED,"Không tồn tại quyền");
        }
        ModelMapper modelMapper = new ModelMapper();
        Role role = modelMapper.map(roleDto, Role.class);
        if (roleRepository.saveOrUpdate(role)) {
            return new ServiceResponse(StatusCode.SUCCESS,"Thành Công");
        }
        return new ServiceResponse(StatusCode.FAILED,"Thêm quyền thất bại");
    }

}
