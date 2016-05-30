package com.test.firebase.products;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.test.firebase.R;
import com.test.firebase.model.Product;
import com.test.firebase.model.ProductList;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ProductsFragment extends Fragment implements IProductsView,ValueEventListener {

    @Bind(R.id.products_recycler_view)
    RecyclerView recyclerView;

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
        FirebaseDatabase.getInstance().getReference().addValueEventListener(this);
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        ProductList productList = dataSnapshot.getValue(ProductList.class);
        loadView(productList);
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        Toast.makeText(getContext(),databaseError.getMessage(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadView(ProductList productList) {
        ProductsAdapter adapter = new ProductsAdapter(productList);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

}
