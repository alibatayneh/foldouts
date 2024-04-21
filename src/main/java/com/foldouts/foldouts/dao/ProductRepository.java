package com.foldouts.foldouts.dao;

import com.foldouts.foldouts.data.Product;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, ObjectId> {

    Product findByProductId(ObjectId productId);
}
