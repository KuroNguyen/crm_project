package com.myclass.service;

import com.myclass.dto.JobDto;
import com.myclass.dto.TaskDto;
import com.myclass.entity.Job;
import com.myclass.entity.User;

import java.util.List;

/**
 * Author: Nguyễn Chánh Trực
 * Created: 1/1/2021
 * Updated: 1/1/2021
 */
public interface JobService {

    List<JobDto> findAll();

    JobDto findById(int id);

    ServiceResponse deleteById(int id);

    ServiceResponse save(JobDto dto);

    ServiceResponse update(JobDto dto);

    ServiceResponse findJobStatisticById(int id);

}
