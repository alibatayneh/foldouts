package com.foldouts.foldouts.service.impl;


import com.foldouts.foldouts.dao.CustomerRepository;
import com.foldouts.foldouts.data.Customer;
import com.foldouts.foldouts.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer create(Customer customer) {
        return customerRepository.insert(customer);
    }

    @Override
    public Customer update(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer readByPhoneNumber(String customerPhoneNumber) {

        return customerRepository.findByPhoneNumber(customerPhoneNumber);
    }
}
