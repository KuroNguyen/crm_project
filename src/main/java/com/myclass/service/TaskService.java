package com.myclass.service;

import com.myclass.dto.TaskDto;
import com.myclass.entity.Job;
import com.myclass.entity.Status;
import com.myclass.entity.User;
import com.myclass.repository.TaskRepository;

import java.util.List;

/**
 * Author: Nguyễn Chánh Trực
 * Created: 12/26/2020
 * Updated: 12/26/2020
 */
public interface TaskService {

    List<TaskDto> findAll();

    List<TaskDto> findAllByUserId(int userId);

    TaskDto findById(int id);

    ServiceResponse deleteById(int id);

    ServiceResponse save(TaskDto dto);

    ServiceResponse update(TaskDto dto, String role);

    List<Job> findAllJobs();

    List<User> findAllUsers();

    List<Status> findAllStatuses();

}
