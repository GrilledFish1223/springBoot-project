package com.ping.springbootdatajpa.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ping.springbootdatajpa.entity.EntityListener;
import com.ping.springbootdatajpa.utils.DateUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @version $Id AbstractEntity.java, v 1.0 2019-11-08 上午11:34 zsp $$
 * @author: zsp
 */
@MappedSuperclass
@EntityListeners(EntityListener.class)
public class AbstractEntity<ID extends Serializable> {
    @CreatedDate
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = DateUtils.DEFAULT_DATETIME_FORMAT)
    private Date creationDate;

    @LastModifiedDate
    @Column(name = "last_changed_date")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = DateUtils.DEFAULT_DATETIME_FORMAT)
    private Date lastChangedDate;

    @Version
    @Column(nullable = false)
    private Long version;

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getLastChangedDate() {
        return lastChangedDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setLastChangedDate(Date lastChangedDate) {
        this.lastChangedDate = lastChangedDate;
    }

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return (mapper.writeValueAsString(this));
        } catch (JsonProcessingException e) {
            return "";
        }
    }
}
