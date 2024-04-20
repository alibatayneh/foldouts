package com.foldouts.foldouts.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.foldouts.foldouts.dao.ProductRepository;
import com.foldouts.foldouts.data.Customer;
import com.foldouts.foldouts.data.Product;
import com.foldouts.foldouts.data.TwimlResponse;
import com.foldouts.foldouts.data.Message;
import com.foldouts.foldouts.service.CustomerService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    //    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/wish-list/{phoneNumber}")
    public ResponseEntity<Object> createProduct(@PathVariable String phoneNumber) {
        try {
            String message = "Success created product!";

            // Create a JSON object to return in the response body
            Customer customer = customerService.readByPhoneNumber(phoneNumber);
            List<Product> wishList = customer.getProductWishList();

            HashMap<String, Product> productMap = new HashMap<>();

            for (Product product : wishList) {
                String productId = product.getProductId();
                productMap.put(productId, product);
            }

            return ResponseEntity.ok(productMap);
        } catch (Exception e) {
            System.err.println("Error getting customer wish list!: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error getting customer wish list!");
        }
    }

    @PutMapping("/wish-list/{phoneNumber}")
    public ResponseEntity<String> removeProductFromWishList(@PathVariable String phoneNumber, @RequestBody HashMap<String, String> body) {
        try {
            String message = "Successfully removed product from wishlist!";
            String removedProductId = body.get("productId");

            // Create a JSON object to return in the response body
            Customer customer = customerService.readByPhoneNumber(phoneNumber);
            List<Product> wishList = customer.getProductWishList();

            List<Product> newWishList = new ArrayList<>();

            for (Product product : wishList) {
                String productId = product.getProductId();
                if (!productId.equals(removedProductId)) {
                    newWishList.add(product);
                }
            }

            customer.setProductWishList(newWishList);
            customerService.update(customer);

            return ResponseEntity.ok(message);
        } catch (Exception e) {
            System.err.println("Error getting customer wish list!: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error getting customer wish list!");
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