package com.myclass.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myclass.common.StatusCode;
import com.myclass.common.TaskStatusConstant;
import com.myclass.dto.TaskDto;
import com.myclass.dto.UserDto;
import com.myclass.dto.UserLoginDto;
import com.myclass.dto.statistic.StatisticDto;
import com.myclass.dto.statistic.StatusStatisticDto;
import com.myclass.entity.User;
import com.myclass.repository.TaskRepository;
import com.myclass.repository.UserRepository;
import com.myclass.service.ServiceResponse;
import com.myclass.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Author: Nguyễn Chánh Trực
 * Created: 12/25/2020
 * Updated: 12/25/2020
 */
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAllJoin();
    }

    @Override
    public UserDto findById(int id) {
        User user = userRepository.findById(id);
        UserDto userDto = new UserDto(user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getFullName(),
                user.getAvatar(),
                user.getRoleId(),
                user.getRole().getDescription());
        return userDto;
    }

    @Override
    public ServiceResponse checkLogin(String email, String password) {
        UserLoginDto userLoginDto = userRepository.findByEmail(email);
        // Check email and password
        if (userLoginDto == null || !BCrypt.checkpw(password,userLoginDto.getPassword()))
            return new ServiceResponse(StatusCode.FAILED, "Email hoặc mật khẩu không đúng");

        return new ServiceResponse(StatusCode.SUCCESS, userLoginDto);
    }

    @Override
    public ServiceResponse deleteById(int id) {
        if (userRepository.deleteById(id)) {
            return new ServiceResponse(StatusCode.SUCCESS, new String("Thành Công"));
        }
        return new ServiceResponse(StatusCode.FAILED, new String(""));
    }

    @Override
    public ServiceResponse save(UserDto userDto) {
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(userDto, User.class);

        // Encrypt password before storing
        user.setPassword(BCrypt.hashpw(user.getPassword(),BCrypt.gensalt(12)));

        if (userRepository.saveOrUpdate(user)) {
            return new ServiceResponse(StatusCode.SUCCESS,"Thành công");
        }

        return new ServiceResponse(StatusCode.FAILED,"Thất bại");
    }

    @Override
    public ServiceResponse update(UserDto userDto) {
        // Get user to edit
        User oldUser = userRepository.findById(userDto.getId());
        // Check existence
        if (oldUser == null) return new ServiceResponse(StatusCode.FAILED, "Không tồn tại người dùng");
        // Get passwords
        String oldPassword = oldUser.getPassword();
        String newPassword = userDto.getPassword();
        // Map from dto to oldUser
        ModelMapper modelMapper = new ModelMapper();
        oldUser = modelMapper.map(userDto, User.class);
        // Check password if changed or not
        if (newPassword.isEmpty() || newPassword==null) {
            oldUser.setPassword(oldPassword);
        } else {
            oldUser.setPassword(BCrypt.hashpw(newPassword,BCrypt.gensalt(12)));
        }
        // Execute update
        if (userRepository.saveOrUpdate(oldUser)) {
            return new ServiceResponse(StatusCode.SUCCESS,"Thành công");
        }

        return new ServiceResponse(StatusCode.FAILED,"Thất bại");
    }

    // TODO: Create service base class to eliminate duplication
    @Override
    public ServiceResponse findUserStatisticById(int id) {
        int total = 0, todoCount = 0, inProgressCount = 0, doneCount = 0;
        // Get three kind of task list
        List<TaskDto> todoTasks = taskRepository.findByUserIdStatusId(id, TaskStatusConstant.TODO);
        todoCount = todoTasks.size();
        List<TaskDto> inProgressTasks = taskRepository.findByUserIdStatusId(id, TaskStatusConstant.IN_PROGRESS);
        inProgressCount = inProgressTasks.size();
        List<TaskDto> doneTasks = taskRepository.findByUserIdStatusId(id, TaskStatusConstant.DONE);
        doneCount = doneTasks.size();

        // Calculate percentages
        total = todoCount + inProgressCount + doneCount;
        StatusStatisticDto statusStatisticDto = new StatusStatisticDto();
        if (total != 0) {
            statusStatisticDto.setTodoPercentage(100*todoCount/total);
            statusStatisticDto.setInProgressPercentage(100*inProgressCount/total);
            statusStatisticDto.setDonePercentage(100*doneCount/total);
        }

        // Init statisticDto
        StatisticDto statisticDto = new StatisticDto();
        statisticDto.setStatusStatisticDto(statusStatisticDto);
        statisticDto.setTodoTasks(todoTasks);
        statisticDto.setInProgressTasks(inProgressTasks);
        statisticDto.setDoneTasks(doneTasks);

        return new ServiceResponse(StatusCode.SUCCESS, statisticDto);
    }
}
