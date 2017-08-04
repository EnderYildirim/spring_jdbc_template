package com.example.spring.dao;

import java.util.List;

import com.example.spring.model.Customer;

public interface CustomerDAO {

    void insert(Customer c);

    Customer getCustomer(Integer id);

    List<Customer> listCustomers();

    void delete(Integer id);

    void update(Customer c);

}
