package com.ping.springbootdatajpa.dao;

import com.ping.springbootdatajpa.dto.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @version $Id AbstractDao.java, v 1.0 2019-11-08 上午11:43 zsp $$
 * @author: zsp
 */
public abstract class AbstractDao<ID extends Serializable, T, R extends BaseRepository<ID, T>,
        S ,C extends DTOConverter<T, S>> {
    @Autowired
    protected R repository;

    protected C dtoConverter;

    @Transactional(readOnly = true)
    public List<S> list() {
        List<T> list = repository.findAll();
        return dtoConverter.dos2dtos(list);
    }

    @Transactional(readOnly = true)
    public List<S> list(Sort sort) {
        List<T> list = repository.findAll(sort);
        return dtoConverter.dos2dtos(list);
    }

    @Transactional(readOnly = true)
    public List<S> list(ID[] ids) {
        List<T> list = repository.findAllById(Arrays.asList(ids));
        return dtoConverter.dos2dtos(list);
    }

    @Transactional(readOnly = true)
    public List<S> list(Collection<ID> ids) {
        List<T> list =  repository.findAllById(ids);
        return dtoConverter.dos2dtos(list);
    }

    @Transactional(readOnly = true)
    public Page<S> page(Pageable pageable) {
        Page<T> page = repository.findAll(pageable);
        return page.map(dtoConverter::do2dto);
    }

    @Transactional(readOnly = true)
    public T get(ID id) {
        Optional<T> entity = repository.findById(id);
        return entity.orElse(null);
    }

    @Transactional(readOnly = true)
    public long count() {
        return repository.count();
    }

    @Transactional(readOnly = true)
    public boolean exist(ID id) {
        return repository.existsById(id);
    }

    public void flush() {
        repository.flush();
    }

    @Transactional
    public T save(T t) {
        T r = repository.save(t);
        repository.flush();
        return r;
    }

    @Transactional
    @CacheEvict(cacheNames = "pwb_entity", allEntries = true)
    public List<T> save(Collection<T> ts) {
        List<T> t = repository.saveAll(ts);
        repository.flush();
        return t;
    }

    @Transactional
    public List<T> save(T[] ts) {
        List<T> t = repository.saveAll(Arrays.asList(ts));
        repository.flush();
        return t;
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteById(ID id) {
        repository.deleteById(id);
        flush();
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(T t) {
        repository.delete(t);
        flush();
    }

    public R getRepository() {
        return repository;
    }

    public abstract C getDtoConverter();

    @Transactional(readOnly = true)
    public S do2dto(T t) {
        return dtoConverter.do2dto(t);
    }

    @Transactional(readOnly = true)
    public List<S> dos2dtos(List<T> dos) {
        return dtoConverter.dos2dtos(dos);
    }

}
