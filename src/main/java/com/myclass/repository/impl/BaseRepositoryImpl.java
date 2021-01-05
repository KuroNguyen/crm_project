package com.myclass.repository.impl;

import com.myclass.repository.BaseRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author: Nguyễn Chánh Trực
 * Created: 12/25/2020
 * Updated: 12/25/2020
 */
@Transactional(rollbackFor = Exception.class)
public class BaseRepositoryImpl<T,K> implements BaseRepository<T,K> {

    protected SessionFactory sessionFactory;
    Class<T> entity;

    public BaseRepositoryImpl(SessionFactory sessionFactory, Class<T> entity) {
        this.sessionFactory = sessionFactory;
        this.entity = entity;
    }

    @Override
    public List<T> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query<T> query = session.createQuery("FROM " + entity.getSimpleName(), entity);
        return query.list();
    }

    @Override
    public T findById(K id) {
        Session session = sessionFactory.getCurrentSession();
        return session.find(entity, id);
    }

    @Override
    public boolean saveOrUpdate(T entity) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.saveOrUpdate(entity);
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            session.clear();
            return false;
        }
    }

    @Override
    public boolean deleteById(K id) {
        Session session = sessionFactory.getCurrentSession();
        try {
            T obj = session.find(entity, id);
            session.delete(obj);
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            session.clear();
            return false;
        }
    }

}
