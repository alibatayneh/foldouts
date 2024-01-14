package com.foldouts.foldouts;

import com.foldouts.foldouts.dao.ProductRepository;
import com.foldouts.foldouts.data.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DuplicateKeyException;

@SpringBootApplication
public class FoldoutsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoldoutsApplication.class, args);
	}

//	@Bean
//	CommandLineRunner runner(ProductRepository repository) {
//		return args -> {
//			Product product = new Product("0000000001","Jacket", 19.99);
//			try {
//				repository.insert(product);
//			} catch (DuplicateKeyException e) {
//				System.err.println("Duplicate key found. Details: " + e.getMessage());
//			}
//
//		};
//	}
}
