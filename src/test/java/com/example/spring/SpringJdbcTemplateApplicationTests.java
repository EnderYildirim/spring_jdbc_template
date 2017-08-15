package com.example.spring;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.spring.dao.CustomerDAO;
import com.example.spring.model.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringJdbcTemplateApplicationTests {
    
    @Autowired
    private CustomerDAO customerDao;
    
	@Test
	public void testAddCustomer() {
	    Customer c = new Customer();
	    c.setFirstName("A6");
	    c.setLastName("S6");
	    customerDao.insert(c);
	    System.out.println("Customer added.");
	}
	
	@Test
    public void testUpdateCustomer() {
        Customer c = customerDao.getCustomer(1);
        c.setFirstName("A1_Updated");
        customerDao.update(c);
        System.out.println("Customer updated.");
    }
	
	@Test
    public void testDeleteCustomer() {
        customerDao.delete(3);
        System.out.println("Customer deleted.");
        testListCustomer();
    }
	
	@Test
	public void testListCustomer() {
	    List<Customer> customers = customerDao.listCustomers();
	    for (Customer c : customers) {
            System.out.println("-------------------------------");
            System.out.format("Customer ID         = %d\n", c.getId());
            System.out.format("Customer First Name = %s\n", c.getFirstName());
            System.out.format("Customer Last Name  = %s\n", c.getLastName());
            System.out.println("-------------------------------");
        }
	    System.out.println("Customers listed.");
	}

}
