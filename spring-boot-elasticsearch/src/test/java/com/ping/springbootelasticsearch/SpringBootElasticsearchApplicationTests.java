package com.ping.springbootelasticsearch;

import com.ping.springbootelasticsearch.model.Customer;
import com.ping.springbootelasticsearch.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootElasticsearchApplicationTests {

    @Autowired
    private CustomerRepository repository;

    @Test
    public void saveCustomers() {
        repository.save(new Customer("Alice", "北京", 13));
        repository.save(new Customer("Bob", "北京", 23));
        repository.save(new Customer("Ping", "杭州", 26));
        repository.save(new Customer("Bing", "兰州", 25));
        repository.save(new Customer("sumer", "北京", 22));

    }

    @Test
    public void findAllCustomers() {
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Customer customer : repository.findAll()) {
            System.out.println(customer);
        }
    }

}
