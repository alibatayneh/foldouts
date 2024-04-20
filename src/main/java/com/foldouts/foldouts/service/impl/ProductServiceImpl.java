package com.foldouts.foldouts.service.impl;

import com.foldouts.foldouts.dao.ProductRepository;
import com.foldouts.foldouts.data.Product;
import com.foldouts.foldouts.service.ProductService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        return productRepository.insert(product);
    }

    @Override
    public Product read(String productId) {
        ObjectId productObjectId = new ObjectId(productId);
        return productRepository.findByProductId(productObjectId);
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }
}
