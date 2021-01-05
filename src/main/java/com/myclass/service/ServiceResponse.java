package com.myclass.service;

import com.myclass.common.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Author: Nguyễn Chánh Trực
 * Created: 12/25/2020
 * Updated: 12/25/2020
 */
@Data
@AllArgsConstructor
public class ServiceResponse {
    private StatusCode statusCode;
    private Object resObject;
}
