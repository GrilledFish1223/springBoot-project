package com.ping.springbootdatajpa.entity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

/**
 *
 * 实体类监听器
 *
 * @version $Id EntityListener.java, v 1.0 2019-11-08 上午11:35 zsp $$
 * @author: zsp
 */
public class EntityListener {
    private static final String CREATED_DATE_WRITE_METHOD_NAME = "setCreationDate";

    private static final String LAST_MODIFIED_DATE_WRITE_METHOD_NAME = "setLastChangedDate";
    /**
     * 保存前处理
     *
     * @param entity 实体对象
     */
    @PrePersist
    public void prePersist(Object entity) {
        setCreatedDate(entity);
        setListModifiedDate(entity);
    }

    /**
     * 更新前处理
     *
     * @param entity 实体对象
     */
    @PreUpdate
    public void preUpdate(Object entity) {
        setListModifiedDate(entity);
    }

    private void setCreatedDate(Object entity) {
        try {
            Method method = entity.getClass().getMethod(CREATED_DATE_WRITE_METHOD_NAME, Date.class);
            method.invoke(entity, new Date());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void setListModifiedDate(Object entity) {
        try {
            Method method = entity.getClass().getMethod(LAST_MODIFIED_DATE_WRITE_METHOD_NAME, Date.class);
            method.invoke(entity, new Date());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
