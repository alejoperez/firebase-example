package com.test.firebase.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageUtil {

    public static void loadImage(Context context, @NonNull String urlResource, @NonNull ImageView imageView) {
        Glide.with(context).load(urlResource).into(imageView);
    }

}
