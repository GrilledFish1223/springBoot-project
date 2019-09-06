package com.ping.springbootelasticsearch.repository;

import com.ping.springbootelasticsearch.model.Customer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author zsp
 * @version $Id CustomerRepository.java, v 1.0 2019-09-04 17:17 zsp Vl $$
 */
public interface CustomerRepository extends ElasticsearchRepository<Customer, String> {
    List<Customer> findByAddress(String address);
    Customer findByUserName(String userName);
    int deleteByUserName(String userName);
}
