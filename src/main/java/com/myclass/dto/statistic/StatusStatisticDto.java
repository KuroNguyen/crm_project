package com.myclass.dto.statistic;

import lombok.Data;

/**
 * Author: Nguyễn Chánh Trực
 * Created: 1/2/2021
 * Updated: 1/2/2021
 */
@Data
public class StatusStatisticDto {
    private float todoPercentage;
    private float inProgressPercentage;
    private float donePercentage;
}
