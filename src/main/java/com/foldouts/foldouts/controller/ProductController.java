package com.foldouts.foldouts.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.foldouts.foldouts.dao.ProductRepository;
import com.foldouts.foldouts.data.Product;
import com.foldouts.foldouts.data.TwimlResponse;
import com.foldouts.foldouts.data.Message;
import com.foldouts.foldouts.service.ProductService;
import com.foldouts.foldouts.service.ReceiveMessageService;
import jakarta.annotation.security.PermitAll;
import org.bson.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

    // Testing product creation purposes only.
    @Autowired
    private ProductRepository repository;

    @Autowired
    private ProductService productService;

//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/")
    public ResponseEntity<String> createProduct(@RequestBody Map<String, Object> newProduct) {
        try {
            Map<String, Object> dataMap = (Map<String, Object>) newProduct.get("data");
            String name = (String) dataMap.get("name");
            Double price = Double.parseDouble((String) dataMap.get("price"));
            String description = (String) dataMap.get("description");
            String brand = (String) dataMap.get("brand");
            String url = (String) dataMap.get("url");
            ArrayList<Map<String, String>> images = (ArrayList<Map<String, String>>) dataMap.get("images");
            Product product = new Product(name, price, description, brand, url, images);
            productService.create(product);
            return ResponseEntity.ok("Success created product!");
        } catch (Exception e) {
            System.err.println("Error creating product: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating product!");
        }
    }

//    @PostMapping(value = "/testCreate/{id}")
//    public void TestCreate(@PathVariable String id) {
//        Product product = new Product(id,"Test Product", 0.00);
//			try {
//				repository.insert(product);
//			} catch (DuplicateKeyException e) {
//				System.err.println("Duplicate key found. Details: " + e.getMessage());
//			}
//    }
}