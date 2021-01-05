package com.myclass.repository.impl;

import com.myclass.dto.UserDto;
import com.myclass.dto.UserLoginDto;
import com.myclass.entity.User;
import com.myclass.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Author: Nguyễn Chánh Trực
 * Created: 12/25/2020
 * Updated: 12/25/2020
 */
@Component
public class UserRepositoryImpl extends BaseRepositoryImpl<User,Integer> implements UserRepository {

    public UserRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory, User.class);
    }

    @Override
    public List<UserDto> findAllJoin() {
        Session session = sessionFactory.getCurrentSession();
        String hql = "SELECT new com.myclass.dto.UserDto(u.id, u.email, u.password, u.fullName, u.avatar, u.roleId, r.description) FROM User u JOIN Role r ON u.roleId = r.id";
        Query<UserDto> query = session.createQuery(hql);
        return query.list();
    }

    @Override
    public UserLoginDto findByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "SELECT new com.myclass.dto.UserLoginDto(u.id, u.email, u.password, u.fullName, u.avatar, u.roleId, r.name) FROM User u JOIN Role r ON u.roleId = r.id WHERE u.email = :email";
        Query<UserLoginDto> query = session.createQuery(hql);
        query.setParameter("email",email);
        return query.uniqueResult();
    }

}
