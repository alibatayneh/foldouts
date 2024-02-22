package com.foldouts.foldouts.data;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Data
public class Customer {

    @Id
    private String phoneNumber;
    private List<Product> pendingProductAdd;
    private boolean optedIn;
    private List<Product> productWishList;

    public Customer(String phoneNumber){
        this.phoneNumber = phoneNumber;
        this.optedIn = false;
        this.productWishList = new ArrayList<>();
        this.pendingProductAdd = new ArrayList<>();
    }

    public void addPendingProduct(Product product) {
        this.pendingProductAdd.add(product);
    }

    public void addProductToWishlist(Product product) {
        this.productWishList.add(product);
    }

    public String addPendingProductsToWishList() {
        String pendingProductsAddedMessage = "The following products have been added to your wish list! \n";
        for(Product product : this.pendingProductAdd) {
            addProductToWishlist(product);
            pendingProductsAddedMessage += (product.getName() + "\n");
        }
        this.pendingProductAdd = new ArrayList<>();

        return  pendingProductsAddedMessage;
    }
}
