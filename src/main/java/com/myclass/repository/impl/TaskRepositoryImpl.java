package com.myclass.repository.impl;

import com.myclass.dto.TaskDto;
import com.myclass.entity.Task;
import com.myclass.repository.TaskRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Author: Nguyễn Chánh Trực
 * Created: 12/27/2020
 * Updated: 12/27/2020
 */
@Component
public class TaskRepositoryImpl extends BaseRepositoryImpl<Task,Integer> implements TaskRepository {

    public TaskRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Task.class);
    }

    @Override
    public List<TaskDto> findAllJoin() {
        Session session = sessionFactory.getCurrentSession();
        String hql = "SELECT new com.myclass.dto.TaskDto(t.id, t.name, t.startDate, t.endDate, t.userId, t.jobId, t.statusId, u.fullName, j.name, s.name) " +
                "FROM Task t JOIN t.user u JOIN t.job j JOIN t.status s";
        Query<TaskDto> query = session.createQuery(hql);
        return query.list();
    }

    @Override
    public List<TaskDto> findAllJoinByUserId(int userId) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "SELECT new com.myclass.dto.TaskDto(t.id, t.name, t.startDate, t.endDate, t.userId, t.jobId, t.statusId, u.fullName, j.name, s.name) " +
                "FROM Task t JOIN t.user u JOIN t.job j JOIN t.status s WHERE t.userId = :userId";
        Query<TaskDto> query = session.createQuery(hql);
        query.setParameter("userId", userId);
        return query.list();
    }

    @Override
    public List<TaskDto> findByJobIdStatusId(int jobId, int statusId) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "SELECT new com.myclass.dto.TaskDto(t.id, t.name, t.startDate, t.endDate, t.userId, t.jobId, t.statusId, u.fullName, j.name, s.name) " +
                "FROM Task t JOIN t.user u JOIN t.job j JOIN t.status s WHERE t.jobId = :jobId AND t.statusId = :statusId";
        Query<TaskDto> query = session.createQuery(hql);
        query.setParameter("jobId", jobId);
        query.setParameter("statusId", statusId);
        return query.list();
    }

    @Override
    public List<TaskDto> findByUserIdStatusId(int userId, int statusId) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "SELECT new com.myclass.dto.TaskDto(t.id, t.name, t.startDate, t.endDate, t.userId, t.jobId, t.statusId, u.fullName, j.name, s.name) " +
                "FROM Task t JOIN t.user u JOIN t.job j JOIN t.status s WHERE t.userId = :userId AND t.statusId = :statusId";
        Query<TaskDto> query = session.createQuery(hql);
        query.setParameter("userId", userId);
        query.setParameter("statusId", statusId);
        return query.list();
    }
}
