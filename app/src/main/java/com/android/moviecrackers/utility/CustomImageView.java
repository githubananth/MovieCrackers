package com.android.moviecrackers.utility;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class CustomImageView {
    public static void ImageViewURL(String url, ImageView imageView, int placeHolderImage) {
        Picasso.get()
                .load(url)
                .placeholder(placeHolderImage)
                .into(imageView);
    }

    public static void ImageViewDrawable(int drawable, ImageView imageView, int placeHolderImage) {
        Picasso.get()
                .load(drawable)
                .placeholder(placeHolderImage)
                .into(imageView);
    }
}