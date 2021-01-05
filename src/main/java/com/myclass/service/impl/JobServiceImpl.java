package com.myclass.service.impl;

import com.myclass.common.DateUtils;
import com.myclass.common.StatusCode;
import com.myclass.common.TaskStatusConstant;
import com.myclass.dto.JobDto;
import com.myclass.dto.TaskDto;
import com.myclass.dto.statistic.StatisticDto;
import com.myclass.dto.statistic.StatusStatisticDto;
import com.myclass.entity.Job;
import com.myclass.repository.JobRepository;
import com.myclass.repository.TaskRepository;
import com.myclass.service.JobService;
import com.myclass.service.ServiceResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Nguyễn Chánh Trực
 * Created: 1/1/2021
 * Updated: 1/1/2021
 */
@Component
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private TaskRepository taskRepository;

//    public JobServiceImpl(JobRepository jobRepository) {
//        this.jobRepository = jobRepository;
//    }

    @Override
    public List<JobDto> findAll() {
        // Get job entities
        List<Job> entities = jobRepository.findAll();
        // Mapping list of entity to list of dto
        List<JobDto> dtos = new ArrayList<>();
        for (Job entity:entities) {
            JobDto dto = new JobDto();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setStartDate(DateUtils.dateToString(entity.getStartDate()));
            dto.setEndDate(DateUtils.dateToString(entity.getEndDate()));
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public JobDto findById(int id) {
        // Mapping entity to dto
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(jobRepository.findById(id),JobDto.class);
    }

    @Override
    public ServiceResponse deleteById(int id) {
        // Check existence
        if (jobRepository.findById(id) == null)
            return new ServiceResponse(StatusCode.FAILED, "Không tồn tại đầu việc");

        if (jobRepository.deleteById(id)) {
            return new ServiceResponse(StatusCode.SUCCESS, "Thành Công");
        }
        return new ServiceResponse(StatusCode.FAILED, "Xóa thất bại");
    }

    @Override
    public ServiceResponse save(JobDto dto) {
        // Check existence
        if (jobRepository.findById(dto.getId()) != null)
            return new ServiceResponse(StatusCode.FAILED, "Đã tồn tại dự án");

        // Map from dto to entity
        Job entity = new Job();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setStartDate(DateUtils.stringToDate(dto.getStartDate()));
        entity.setEndDate(DateUtils.stringToDate(dto.getEndDate()));

        if (jobRepository.saveOrUpdate(entity)) {
            return new ServiceResponse(StatusCode.SUCCESS, "Thành Công");
        }
        return new ServiceResponse(StatusCode.FAILED, "Thêm mới thất bại");
    }

    @Override
    public ServiceResponse update(JobDto dto) {
        // Get job to edit
        Job oldJob = jobRepository.findById(dto.getId());
        // Check existence
        if (oldJob == null)
            return new ServiceResponse(StatusCode.FAILED, "Không tồn tại dự án");
        // Map from dto to entity
        oldJob.setId(dto.getId());
        oldJob.setName(dto.getName());
        oldJob.setStartDate(DateUtils.stringToDate(dto.getStartDate()));
        oldJob.setEndDate(DateUtils.stringToDate(dto.getEndDate()));

        if (jobRepository.saveOrUpdate(oldJob)) {
            return new ServiceResponse(StatusCode.SUCCESS, "Thành Công");
        }
        return new ServiceResponse(StatusCode.FAILED, "Cập nhật thất bại");
    }

    @Override
    public ServiceResponse findJobStatisticById(int id) {
        int total = 0, todoCount = 0, inProgressCount = 0, doneCount = 0;
        // Get three kind of task list
        List<TaskDto> todoTasks = taskRepository.findByJobIdStatusId(id, TaskStatusConstant.TODO);
        todoCount = todoTasks.size();
        List<TaskDto> inProgressTasks = taskRepository.findByJobIdStatusId(id, TaskStatusConstant.IN_PROGRESS);
        inProgressCount = inProgressTasks.size();
        List<TaskDto> doneTasks = taskRepository.findByJobIdStatusId(id, TaskStatusConstant.DONE);
        doneCount = doneTasks.size();

        // Calculate percentages
        total = todoCount + inProgressCount + doneCount;
        StatusStatisticDto statisticDto = new StatusStatisticDto();
        if (total != 0) {
            statisticDto.setTodoPercentage(100*todoCount/total);
            statisticDto.setInProgressPercentage(100*inProgressCount/total);
            statisticDto.setDonePercentage(100*doneCount/total);
        }

        // Init jobStatisticDto
        StatisticDto jobStatisticDto = new StatisticDto();
        jobStatisticDto.setStatusStatisticDto(statisticDto);
        jobStatisticDto.setTodoTasks(todoTasks);
        jobStatisticDto.setInProgressTasks(inProgressTasks);
        jobStatisticDto.setDoneTasks(doneTasks);

        return new ServiceResponse(StatusCode.SUCCESS, jobStatisticDto);
    }
}
