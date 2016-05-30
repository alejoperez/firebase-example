package com.test.firebase.products;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.test.firebase.R;
import com.test.firebase.model.ProductList;


import butterknife.Bind;
import butterknife.ButterKnife;

public class ProductsFragment extends Fragment implements IProductsView {

    @Bind(R.id.products_recycler_view)
    RecyclerView recyclerView;

    private ProductsAdapter adapter;

    public static ProductsFragment newInstance() {
        return new ProductsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products, container, false);
        ButterKnife.bind(this, view);
        loadData();
        return view;
    }

    @Override
    public void loadData() {
        IProductsPresenter productsPresenter = new ProductsPresenter(this);
        productsPresenter.loadProducts();
    }

    @Override
    public void onLoadProductsSuccess(ProductList productList) {
        if (adapter != null) {
            adapter.setProductList(productList);
        } else {
            adapter = new ProductsAdapter(productList);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(adapter);
        }
    }

    public void onLoadProductsFail() {
        Toast.makeText(getContext(),R.string.products_load_fail,Toast.LENGTH_SHORT).show();
    }

}
