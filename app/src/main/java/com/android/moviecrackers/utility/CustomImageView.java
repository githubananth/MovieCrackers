package com.android.moviecrackers.utility;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class CustomImageView {
    /**
     * Create common class for the showing images from the url, where as we are using Piccaso library
     * @param url
     * @param imageView
     * @param placeHolderImage
     */
    public static void ImageViewURL(String url, ImageView imageView, int placeHolderImage) {
        Picasso.get()
                .load(url)
                .placeholder(placeHolderImage)
                .into(imageView);
    }
}