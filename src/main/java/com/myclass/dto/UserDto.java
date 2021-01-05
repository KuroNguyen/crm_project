package com.myclass.dto;

import lombok.Data;

/**
 * Author: Nguyễn Chánh Trực
 * Created: 12/25/2020
 * Updated: 12/25/2020
 */
@Data
public class UserDto {
    private int id;
    private String email;
    private String password;
    private String fullName;
    private String avatar;
    private int roleId;
    private String roleDescription;

    public UserDto(int id, String email, String password, String fullName, String avatar, int roleId, String roleDescription) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.avatar = avatar;
        this.roleId = roleId;
        this.roleDescription = roleDescription;
    }

    public UserDto(int id, String email, String password, String fullName, String avatar, int roleId) {
        this(id,email,password,fullName,avatar,roleId,"");
    }

    public UserDto(String email, String password, String fullName, String avatar, int roleId) {
        this(0,email,password,fullName,avatar,roleId);
    }


}
