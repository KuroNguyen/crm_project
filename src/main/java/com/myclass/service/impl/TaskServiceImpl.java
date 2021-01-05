package com.myclass.service.impl;

import com.myclass.common.RoleConstant;
import com.myclass.common.StatusCode;
import com.myclass.dto.JobDto;
import com.myclass.dto.TaskDto;
import com.myclass.dto.UserDto;
import com.myclass.entity.Job;
import com.myclass.entity.Status;
import com.myclass.entity.Task;
import com.myclass.entity.User;
import com.myclass.repository.JobRepository;
import com.myclass.repository.StatusRepository;
import com.myclass.repository.TaskRepository;
import com.myclass.repository.UserRepository;
import com.myclass.service.ServiceResponse;
import com.myclass.service.TaskService;
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
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StatusRepository statusRepository;

    @Override
    public List<TaskDto> findAll() {
        return taskRepository.findAllJoin();
    }

    @Override
    public List<TaskDto> findAllByUserId(int userId) {
        return taskRepository.findAllJoinByUserId(userId);
    }

    @Override
    public TaskDto findById(int id) {
        // Get entity from repository
        Task entity = taskRepository.findById(id);
        // Map from entity to dto
        TaskDto dto = new TaskDto(entity.getId(),
                entity.getName(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getUserId(),
                entity.getJobId(),
                entity.getStatusId(),
                entity.getStatus().getName(),
                entity.getUser().getFullName(),
                entity.getJob().getName());
        return dto;
    }

    @Override
    public ServiceResponse deleteById(int id) {
        // Check existence
        if (taskRepository.findById(id) == null)
            return new ServiceResponse(StatusCode.FAILED, "Không tồn tại đầu việc");

        if (taskRepository.deleteById(id)) {
            return new ServiceResponse(StatusCode.SUCCESS, "Thành Công");
        }
        return new ServiceResponse(StatusCode.FAILED, "Xóa thất bại");
    }

    @Override
    public ServiceResponse save(TaskDto dto) {
        // Check existence
        if (taskRepository.findById(dto.getId()) != null)
            return new ServiceResponse(StatusCode.FAILED, "Đã tồn tại đầu việc");

        // Map from dto to entity
        ModelMapper modelMapper = new ModelMapper();
        Task entity = modelMapper.map(dto, Task.class);

        if (taskRepository.saveOrUpdate(entity)) {
            return new ServiceResponse(StatusCode.SUCCESS, "Thành Công");
        }
        return new ServiceResponse(StatusCode.FAILED, "Thêm mới thất bại");
    }

    @Override
    public ServiceResponse update(TaskDto dto, String role) {
        // Get task to edit
        Task oldTask = taskRepository.findById(dto.getId());
        // Check existence
        if (oldTask == null) return new ServiceResponse(StatusCode.FAILED, "Không tồn tại đầu việc");
        // Map from dto to entity
        ModelMapper modelMapper = new ModelMapper();
        Task task = modelMapper.map(dto, Task.class);
        // Response success if unchanged
        if (oldTask.equals(task)) return new ServiceResponse(StatusCode.SUCCESS, "Thành Công");
        // Role check
        if (role.equals(RoleConstant.ROLE_USER)) {
            // Check if only status change
            // Set status and check the objects' equality
            oldTask.setStatusId(task.getStatusId());
            if (oldTask.equals(task)) {
                // Do update
                taskRepository.saveOrUpdate(oldTask);
                return new ServiceResponse(StatusCode.SUCCESS,"Thành Công");
            }
            return new ServiceResponse(StatusCode.UNAUTHORIZED,"Không có quyền");
        }
        if (taskRepository.saveOrUpdate(task)) {
            return new ServiceResponse(StatusCode.SUCCESS, "Thành Công");
        }
        return new ServiceResponse(StatusCode.FAILED, "Cập nhật thất bại");
    }

    @Override
    public List<Job> findAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<Status> findAllStatuses() {
        return statusRepository.findAll();
    }


}
