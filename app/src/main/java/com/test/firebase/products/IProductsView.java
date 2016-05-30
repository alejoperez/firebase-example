package com.test.firebase.products;


import com.test.firebase.model.ProductList;

public interface IProductsView {
    void loadData();
    void onLoadProductsSuccess(ProductList productList);
    void onLoadProductsFail();
}
