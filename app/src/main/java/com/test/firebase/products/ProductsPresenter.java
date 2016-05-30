package com.test.firebase.products;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.test.firebase.model.ProductList;

public class ProductsPresenter implements IProductsPresenter,ValueEventListener {

    private IProductsView productsView;

    public ProductsPresenter(IProductsView productsView) {
        this.productsView = productsView;
    }

    @Override
    public void loadProducts() {
        FirebaseDatabase.getInstance().getReference().addValueEventListener(this);
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        ProductList productList = dataSnapshot.getValue(ProductList.class);
        productsView.onLoadProductsSuccess(productList);
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        productsView.onLoadProductsFail();
    }
}
