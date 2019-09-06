package com.ping.springbootelasticsearch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 *  // @Document 注解会对实体中的所有属性建⽴索引
 * indexName = "customer" 表示创建⼀个名称为 "customer" 的索引
 * type = "customer" 表示在索引中创建⼀个名为 "customer" 的 type
 * shards = 1 表示只使⽤⼀个分⽚
 * replicas = 0 表示不使⽤复制
 * refreshInterval = "-1" 表示禁⽤索引刷新
 *
 * @version $Id Customer.java, v 1.0 2019-09-04 17:10 zsp $$
 * @author: zhangsp
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(indexName = "customer", type = "customer", shards = 1, replicas = 0, refreshInterval = "-1")
public class Customer {
    @Id
    private String id;
    private String userName;
    private String address;
    private int age;

    public Customer(String userName, String address, int age) {
        this.userName = userName;
        this.address = address;
        this.age = age;
    }
}
