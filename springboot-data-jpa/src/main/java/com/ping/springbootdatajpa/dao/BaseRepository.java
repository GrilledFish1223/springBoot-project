package com.ping.springbootdatajpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * @version $Id BaseRepository.java, v 1.0 2019-11-08 上午11:12 zsp $$
 * @author: zsp
 */
@NoRepositoryBean
public interface BaseRepository<ID extends Serializable, T > extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

}
