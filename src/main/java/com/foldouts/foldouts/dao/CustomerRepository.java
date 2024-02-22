package com.foldouts.foldouts.dao;

import com.foldouts.foldouts.data.Customer;
import com.foldouts.foldouts.data.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

    Customer findByPhoneNumber(String customerPhoneNumber);
}
