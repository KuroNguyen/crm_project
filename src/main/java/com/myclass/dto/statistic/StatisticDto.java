package com.myclass.dto.statistic;

import com.myclass.dto.TaskDto;
import lombok.Data;

import java.util.List;

/**
 * Author: Nguyễn Chánh Trực
 * Created: 1/2/2021
 * Updated: 1/2/2021
 */
@Data
public class StatisticDto {
    private StatusStatisticDto statusStatisticDto;
    private List<TaskDto> todoTasks;
    private List<TaskDto> inProgressTasks;
    private List<TaskDto> doneTasks;
}
