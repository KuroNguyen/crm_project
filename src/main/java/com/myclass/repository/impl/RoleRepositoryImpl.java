package com.myclass.repository.impl;

import com.myclass.entity.Role;
import com.myclass.repository.RoleRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


/**
 * Author: Nguyễn Chánh Trực
 * Created: 12/26/2020
 * Updated: 12/26/2020
 */
@Component
@Transactional(rollbackFor = Exception.class)
public class RoleRepositoryImpl extends BaseRepositoryImpl<Role,Integer> implements RoleRepository {

    public RoleRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Role.class);
    }

    @Override
    public Role findByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Role WHERE name = :name";
        Query<Role> query = session.createQuery(hql);
        query.setParameter("name", name);
        return query.uniqueResult();
    }
}
