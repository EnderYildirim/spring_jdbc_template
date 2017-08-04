package com.example.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.example.spring.model.Customer;

public class CustomerDAOImpl implements CustomerDAO {

    private JdbcTemplate jdbcTemplate;

    private final String INSERT_CUSTOMER       = "INSERT INTO customer(fname, lname) VALUES(?, ?)";
    
    private final String DELETE_CUSTOMER       = "DELETE FROM customer WHERE id = ?";
    
    private final String UPDATE_CUSTOMER       = "UPDATE customer SET fname = ?, lname = ? WHERE id = ?";
   
    private final String SELECT_CUSTOMER_BY_ID = "SELECT * FROM customer WHERE id = ?";
    
    private final String ALL_CUSTOMER          = "SELECT * FROM customer";

    public static class CustomerMapper implements RowMapper<Customer> {
        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Customer customer = new Customer();
            customer.setId(rs.getInt("id"));
            customer.setFirstName(rs.getString("fname"));
            customer.setLastName(rs.getString("lname"));
            return customer;
        }
    }

    public CustomerDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insert(Customer c) {
        jdbcTemplate.update(INSERT_CUSTOMER, c.getFirstName(), c.getLastName());
    }

    @Override
    public Customer getCustomer(Integer id) {
        return jdbcTemplate.queryForObject(SELECT_CUSTOMER_BY_ID, new Object[] {id}, new CustomerMapper());
    }

    @Override
    public List<Customer> listCustomers() {
        return jdbcTemplate.query(ALL_CUSTOMER, new CustomerMapper());
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update(DELETE_CUSTOMER, id);
    }

    @Override
    public void update(Customer c) {
        jdbcTemplate.update(UPDATE_CUSTOMER, c.getFirstName(), c.getLastName(), c.getId());
    }

}
