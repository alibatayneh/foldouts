package com.foldouts.foldouts.dao;

import com.foldouts.foldouts.data.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
