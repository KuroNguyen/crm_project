package com.myclass.repository;

import com.myclass.entity.Role;

/**
 * Author: Nguyễn Chánh Trực
 * Created: 12/26/2020
 * Updated: 12/26/2020
 */
public interface RoleRepository extends BaseRepository<Role,Integer> {

    Role findByName(String name);

}
