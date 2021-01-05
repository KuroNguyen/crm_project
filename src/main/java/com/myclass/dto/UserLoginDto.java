package com.myclass.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * Author: Nguyễn Chánh Trực
 * Created: 1/4/2021
 * Updated: 1/4/2021
 */
@Data
@AllArgsConstructor
@ToString
public class UserLoginDto {

    private int id;
    private String email;
    private String password;
    private String fullName;
    private String avatar;
    private int roleId;
    private String roleName;

}
