package com.akyuz.rickandmorty.databinding;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.akyuz.rickandmorty.R;
import com.squareup.picasso.Picasso;

final class ImageBindingAdapter {
    private ImageBindingAdapter() {

    }

    @SuppressWarnings("unchecked")
    @BindingAdapter(value = "imageUrl")
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.ic_place_holder)
                .into(view);
    }

}
