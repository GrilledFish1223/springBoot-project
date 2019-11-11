package com.ping.springbootdatajpa.controller;

import com.ping.springbootdatajpa.dto.Rest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;


/**
 * @author <a href="mailto:wangchunjs@dangdang.com">Wang Chun</a>
 * @date 2019-01-22 11:39
 */
public abstract class AbstractController {

    protected Supplier<Rest<Map<String, Object>>> mapSupplier = Rest::new;
    protected Supplier<Rest<List<Map<String, Object>>>> mapListSupplier = Rest::new;
    protected Supplier<Rest<Page<Map<String, Object>>>> mapPageSupplier = Rest::new;

    @Value("${page.size:20}")
    protected Integer pageSize;

    public static String defaultSortProperty = "lastChangedDate";
    public static String defaultSortProperty2 = "creationDate";

    public Integer getPageSize(){
        if (pageSize == null) {
            this.pageSize = 20;
        }
        return pageSize;
    }

    public Pageable pageable(Integer page, Integer size, Sort sort) {
        if(page == null || page < 0){
            page = 0;
        }
        if(size == null || size <= 0){
            size = getPageSize();
        }
        return PageRequest.of(page, size, sort);
    }

    public Pageable pageable(Integer page, Integer size) {
        return pageable(page, size, Sort.by(Sort.Direction.DESC, defaultSortProperty));
    }

    public Pageable pageable(Integer page) {
        return pageable(page, getPageSize());
    }

}
