package com.myclass.repository;

import com.myclass.dto.TaskDto;
import com.myclass.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author: Nguyễn Chánh Trực
 * Created: 12/26/2020
 * Updated: 12/26/2020
 */
@Component
public interface TaskRepository extends BaseRepository<Task,Integer>{

    List<TaskDto> findAllJoin();

    List<TaskDto> findAllJoinByUserId(int userId);

    List<TaskDto> findByJobIdStatusId(int jobId, int statusId);

    List<TaskDto> findByUserIdStatusId(int userId, int statusId);
}
