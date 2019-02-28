package com.drools.entity;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Order {

    private Customer customer;

    private List<Product> products;

    private double totalPrice;

    public Order(Customer customer) {
        super();
        this.customer = customer;
        products = new ArrayList<Product>();
    }

    public void addProduct(Product product1) {
        products.add(product1);
    }
}