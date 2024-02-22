package com.foldouts.foldouts.service;

import com.foldouts.foldouts.data.Product;

public interface ProductService {
    Product read(String productId);
    Product create(Product product);
    Product update(Product product);

}
