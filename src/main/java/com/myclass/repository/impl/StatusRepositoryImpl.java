package com.myclass.repository.impl;

import com.myclass.entity.Status;
import com.myclass.repository.StatusRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

/**
 * Author: Nguyễn Chánh Trực
 * Created: 1/2/2021
 * Updated: 1/2/2021
 */
@Component
public class StatusRepositoryImpl extends BaseRepositoryImpl<Status,Integer> implements StatusRepository {
    public StatusRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Status.class);
    }
}
