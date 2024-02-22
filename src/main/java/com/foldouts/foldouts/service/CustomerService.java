package com.foldouts.foldouts.service;

import com.foldouts.foldouts.data.Customer;

public interface CustomerService {
    Customer readByPhoneNumber(String customerPhoneNumber);
    Customer create(Customer customer);
    Customer update(Customer customer);

}
