package com.myclass.repository.impl;

import com.myclass.entity.Job;
import com.myclass.repository.JobRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

/**
 * Author: Nguyễn Chánh Trực
 * Created: 1/1/2021
 * Updated: 1/1/2021
 */
@Component
public class JobRepositoryImpl extends BaseRepositoryImpl<Job,Integer> implements JobRepository {
    public JobRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Job.class);
    }
}
