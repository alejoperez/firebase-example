package com.test.firebase.products;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.test.firebase.model.Product;
import com.test.firebase.model.ProductList;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    private ProductList productList;

    public ProductsAdapter(ProductList productList) {
        this.productList = productList;
    }

    public void setProductList(ProductList productList) {
        this.productList = productList;
        notifyDataSetChanged();
    }

    @Override
    public ProductsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ProductView productView = new ProductView(parent.getContext());
        return new ViewHolder(productView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(productList.getProducts().get(position));
    }

    @Override
    public int getItemCount() {
        return productList.getProducts().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ProductView productView;

        public ViewHolder(ProductView productView) {
            super(productView);
            this.productView = productView;
        }

        public void bind(Product product) {
            productView.bind(product);
        }
    }
}
