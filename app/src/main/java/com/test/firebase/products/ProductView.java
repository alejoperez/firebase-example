package com.test.firebase.products;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.test.firebase.R;
import com.test.firebase.model.Product;
import com.test.firebase.util.ImageUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ProductView extends RelativeLayout {

    @Bind(R.id.product_image_view)
    ImageView imageView;

    @Bind(R.id.product_text_view_name)
    TextView textViewName;

    @Bind(R.id.product_text_view_description)
    TextView textViewDescription;

    public ProductView(Context context) {
        super(context);
        loadView();
    }

    public ProductView(Context context, AttributeSet attrs) {
        super(context, attrs);
        loadView();
    }

    public ProductView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        loadView();
    }

    private void loadView() {
        inflate(getContext(), R.layout.view_product, this);
        ButterKnife.bind(this);
    }

    public void bind(Product product) {
        textViewName.setText(product.getName());
        textViewDescription.setText(product.getDescription());
        ImageUtil.loadImage(getContext(),"http://pngimg.com/upload/pear_PNG3446.png",imageView);
    }

}
