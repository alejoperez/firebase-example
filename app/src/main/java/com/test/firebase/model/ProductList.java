package com.test.firebase.model;


import java.util.ArrayList;
import java.util.List;

public class ProductList {

    private List<Product> products = new ArrayList<>();

    public ProductList() {
    }

    public ProductList(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
