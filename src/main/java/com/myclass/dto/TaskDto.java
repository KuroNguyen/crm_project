package com.myclass.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

/**
 * Author: Nguyễn Chánh Trực
 * Created: 12/25/2020
 * Updated: 12/25/2020
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

    private int id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private int userId;
    private int jobId;
    private int statusId;
    private String userName;
    private String jobName;
    private String statusName;

}
