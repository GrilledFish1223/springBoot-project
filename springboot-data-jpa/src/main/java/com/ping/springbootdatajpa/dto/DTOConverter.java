package com.ping.springbootdatajpa.dto;

import java.util.List;

/**
 * @version $Id DTOConverter.java, v 1.0 2019-11-08 上午11:50 zsp $$
 * @author: zsp
 */
public interface DTOConverter<T, S>  {
    S do2dto(T entity);

    List<S> dos2dtos(List<T> list);
}
