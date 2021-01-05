package com.myclass.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Author: Nguyễn Chánh Trực
 * Created: 12/29/2020
 * Updated: 12/29/2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobDto {

    private int id;
    private String name;
    private String startDate;
    private String endDate;

}
