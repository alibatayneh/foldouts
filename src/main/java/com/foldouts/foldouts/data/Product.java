package com.foldouts.foldouts.data;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@Document
public class Product {
    @Id
    private ObjectId productId;
    private String name;
    private Double price;
    private String brand;
    private String description;
    private String url;
    private ArrayList<Map<String,String>> images;
    private Integer wishListAddCount;
    private Integer buyButtonCount;

    public Product(String name, Double price, String description, String brand, String url, ArrayList<Map<String,String>> images) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.brand = brand;
        this.url = url;
        this.images = images;
        this.wishListAddCount = 0;
        this.buyButtonCount = 0;

    }

    public String getProductId() {
        return this.productId.toString();
    }

}
