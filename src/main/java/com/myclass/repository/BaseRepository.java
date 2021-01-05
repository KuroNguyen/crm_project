package com.myclass.repository;

import java.util.List;

/**
 * Author: Nguyễn Chánh Trực
 * Created: 12/25/2020
 * Updated: 12/25/2020
 */
public interface BaseRepository<T,K> {

    List<T> findAll();

    T findById(K id);

    boolean saveOrUpdate(T entity);

    boolean deleteById(K id);

}
